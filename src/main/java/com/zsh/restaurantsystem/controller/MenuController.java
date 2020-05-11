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
    public Map setMenu(@RequestBody Menu menu, HttpServletResponse response) {
        menuService.setMenu(menu);
        return Map.of("menus", menuService.getAllMenu());
    }

    //上传文件
    @ResponseBody
    @RequestMapping(value = "/fileUpload/{mid}", produces = "image/png")
    public void upload(@RequestParam("file") MultipartFile file, @PathVariable("mid") int id) {
        //获得文件名字
        String fileName = file.getOriginalFilename();
        //上传失败提示
        String warning = "";
        String name = FileUtils.upload(file, path, fileName);
        if (!name.equals("0")) {
            //上传成功
            warning = "上传成功";
            Menu m = menuService.getMenuById(id);
            m.setPic(name);
            menuService.setMenu(m);

        } else {
            warning = "上传失败";
        }
        System.out.println(warning);

    }


    @GetMapping("/delete/{mid}")
    public Map deleteMenu(@PathVariable int mid) {

        return Map.of("menus", menuService.deleteMenu(mid));
    }

    @PostMapping("/update")
    public Map updateMenu(@RequestBody Menu menu, HttpServletResponse response) {

        return Map.of("menus", menuService.updateMenu(menu));
    }

    @PostMapping("/get")
    public Map getOne(@RequestBody Menu menu, HttpServletResponse response) {
        return Map.of("menu", menuService.getMenu(menu));
    }

    @GetMapping("/get_all/{type}")
    public Map getAll(@PathVariable int type) {
        return Map.of("menus", menuService.getMenuByType(type));
    }

    @RequestMapping(value = "/show/{fileName}", produces = "image/png")
    public ResponseEntity show(@PathVariable("fileName") String fileName) {


        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + "/" + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
