package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.service.ListService;
import com.zsh.restaurantsystem.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/user2")
public class User2Controller {
    @Autowired
    ListService listService;
    @GetMapping("/getByStatue")
    public Map getByStatue() {
        return Map.of("lists",listService.getNowListByOrder()) ;
    }
    @GetMapping("/setByStatue/{lid}/{statue}")
    public Map setByStatue(@PathVariable int lid,@PathVariable int statue) {
        listService.setListStatue(statue, lid);
        return Map.of("lists",listService.getNowListByOrder()) ;
    }
}
