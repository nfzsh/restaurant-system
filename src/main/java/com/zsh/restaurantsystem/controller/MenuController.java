package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Menu;
import com.zsh.restaurantsystem.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

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
}
