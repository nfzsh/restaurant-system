package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Menu;
import com.zsh.restaurantsystem.service.MenuService;
import com.zsh.restaurantsystem.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
//

    private final ResourceLoader resourceLoader;

    public MenuController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;
//

    @PostMapping("/add")
    public Map setMenu(@RequestBody Menu menu, HttpServletResponse response){
        int a = menuService.setMenu(menu);
        return Map.of("message",a);
    }

    @PostMapping("/delete")
    public void deleteMenu(@RequestBody Menu menu,HttpServletResponse response){
        menuService.deleteMenu(menu);
    }

    @PostMapping("/update")
    public Map updateMenu(@RequestBody Menu menu,HttpServletResponse response){
        int a = menuService.updateMenu(menu);
        return Map.of("message",a);
    }

    @PostMapping("/get")
    public Map getOne(@RequestBody Menu menu,HttpServletResponse response){
        return Map.of("menu",menuService.getMenu(menu));
    }

    @GetMapping("/get_all")
    public Map getAll(){
        return Map.of("menus",menuService.getAllMenu());
    }

    //上传文件
    @ResponseBody
    @RequestMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file ){
        //1定义要上传文件 的存放路径
        String localPath="D:/image";
        //2获得文件名字
        String fileName=file.getOriginalFilename();
        //2上传失败提示
        String warning="";
        if(FileUtils.upload(file, localPath, fileName)){
            //上传成功
            warning="上传成功";

        }else{
            warning="上传失败";
        }
        System.out.println(warning);
        return "上传成功";
    }

    @RequestMapping("show")
    public ResponseEntity  show(String fileName){


        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
