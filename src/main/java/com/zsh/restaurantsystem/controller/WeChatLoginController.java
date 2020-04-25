package com.zsh.restaurantsystem.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/wechat")
public class WeChatLoginController {

    @Value("${wechat.appid}")
    private String appid;
    @Value("${wechat.appsecret}")
    private String appsecret;

    private String openid;
    private String session_key;

    @PostMapping("/index")
    private String login(@RequestParam String code) {
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

        System.out.println("session_key==" + session_key);
        System.out.println("openid==" + openid);
        return resultString;
    }

}
