package com.zsh.restaurantsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.zsh.restaurantsystem.component.EncryptorComponent;
import com.zsh.restaurantsystem.entity.RedisQueue;
import com.zsh.restaurantsystem.entity.TableNum;
import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.RedisRepository;
import com.zsh.restaurantsystem.service.User1Service;
import com.zsh.restaurantsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wechat")
public class WeChatLoginController {

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;

    private String openid;
    private String session_key;
    @Autowired
    private UserService userService;
    @Autowired
    private User1Service user1Service;
    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    private String login(@RequestParam String code, HttpServletResponse httpServletResponse) {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder
                    builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        session_key = jsonObject.get("session_key") + "";
        openid = jsonObject.get("openid") + "";
        User user = userService.getUserByOpenid(openid);

        if (user == null) {
            user = new User();
            user.setOpenid(openid);
        }
        user.setSession_key(session_key);
        userService.setUser(user);
        user = userService.getUserByOpenid(openid);
        Map map = Map.of("uid", user.getId());
        // 生成加密token
        String token = encryptorComponent.encrypt(map);
        // 在header创建自定义的权限
        httpServletResponse.setHeader("token", token);
        System.out.println("session_key==" + session_key);
        System.out.println("openid==" + openid);
        return resultString;
    }

    @PostMapping("/getUserInfo")
    private void getUserInfo(@RequestBody User user,
                             @RequestAttribute int uid) {

        user.setId(uid);
        userService.updateUser(user);
        System.out.println(user);
    }

    //获取全部类型桌子及数量
    @GetMapping("/getTableNumAll")
    public Map getTableNumAll() {
        return Map.of("tableNumAll", user1Service.findTableNumByStatue());
    }

    //获取各种类型桌子剩余数量
    @GetMapping("/getTableNum")
    public Map getTableNum() {
        return Map.of("tableNum", user1Service.findTableNumByStatueWx());
    }

    //排队
    @GetMapping("/add/{tn}")
    public Map add(
            @RequestAttribute String uid, @PathVariable int tn) {
        String a = redisRepository.incr(redisRepository.getCurrent2TodayEndMillisTime()).toString();
        a = tn + "0" + a;
        RedisQueue redisQueue = new RedisQueue(uid, a);
        redisRepository.add(redisQueue, tn);
        return Map.of("num", a);
    }
    //查询前面人数
    @GetMapping("/selectIndex")
    public Map selectIndex(@RequestAttribute String uid) {
        List<TableNum> l = user1Service.findTableNumByStatueWx();
        for(int i =0;i< l.size();i++){
            if(l.get(i).getCount()==0&&redisRepository.findOne(uid, l.get(i).getNum())!=-1){
                String numb = redisRepository.findOneNum(uid,l.get(i).getNum());
                return Map.of('n',l.get(i).getNum(),'m', redisRepository.findOne(uid, l.get(i).getNum()),"num",numb);
            }
        }
        return Map.of('m',-1);
    }
    //获取某桌当前点餐情况
    @GetMapping("getList/{tid}")
    public Map getListByTable(@PathVariable int tid) {
        return Map.of("lists",user1Service.getListsByTable(tid));
    }

}
