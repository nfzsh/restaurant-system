package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.service.ListService;
import com.zsh.restaurantsystem.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        return Map.of("lists", listService.getNowListByOrder());
    }

    @GetMapping("/setByStatue/{lid}/{statue}")
    public Map setByStatue(@PathVariable int lid, @PathVariable int statue, @RequestAttribute int uid, HttpServletResponse response) {
        if (statue == 1) {
            if (listService.getListById(lid).getStatue() == 1) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "该菜品已被接受");
            } else {
                Admin admin = new Admin();
                admin.setId(uid);
                listService.setAdmin(admin, lid);
            }
        }
        listService.setListStatue(statue, lid);
        return Map.of("lists", listService.getNowListByOrder());
    }
}
