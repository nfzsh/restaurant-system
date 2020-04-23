package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/User")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/select_all")
    public Map getAll() {
        return Map.of("users", userService.getAll());
    }

    @PostMapping("/select_id")
    public Map getUser(@RequestBody User user, HttpServletResponse response) {
        return Map.of("User", userService.getUserByOpenid(user.getOpenid()));
    }

    @PostMapping("/select")
    public Map getUser(@RequestParam String s, int a, HttpServletResponse response) {
        if (a == 1)
            return Map.of("users", userService.getUserByName(s));
        else
            return Map.of("users", userService.getUserByPh(s));
    }

    @PostMapping("/add")
    public void setUser(@RequestBody User user, HttpServletResponse response) {
        userService.setUser(user);
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user, HttpServletResponse response) {
        //带ID
        user.setId(userService.getUserByOpenid(user.getOpenid()).getId());
        userService.setUser(user);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody User user, HttpServletResponse response) {
        userService.deleteUser(user.getId());
    }
}
