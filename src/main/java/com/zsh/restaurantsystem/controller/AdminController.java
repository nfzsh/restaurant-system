package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.service.AdminService;
import com.zsh.restaurantsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/select/{name}")
    public Map selectAdmin(@PathVariable String name,HttpServletResponse response) {
        return Map.of("admin",adminService.getAdmin(name)) ;
    }

    @PostMapping("/add")
    public Map addAdmin(@RequestBody Admin admin) {
        Optional.ofNullable(admin)
                .ifPresentOrElse(u -> {
                    String swd;
                    System.out.println(admin.getPassword());
                    swd = passwordEncoder.encode(admin.getPassword());
                    adminService.setAdmin(admin.getName(), swd,
                            admin.getAuthority());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "信息不能为空！");
                });
        return Map.of("admins",adminService.getAllAdmin());
    }

    @GetMapping("/select_all")
    public Map getAllAdmin(){
        return Map.of("admins",adminService.getAllAdmin());
    }

    @GetMapping("/delete/{aid}")
    public Map deleteAdmin(@PathVariable int aid,HttpServletResponse response){
        return Map.of("admins",adminService.DeleteAdmin(aid));
    }

    @PostMapping("/update")
    public Map updateAdmin(@RequestBody Admin admin,HttpServletResponse response){
        String pwd = "";
        if(admin.getPassword().equals(""))
            ;
        else
        pwd = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(pwd);
        adminService.UpdateAdmin(admin);
        return Map.of("admins",adminService.getAllAdmin());
    }
    @GetMapping("/select_users")
    public Map getAll() {
        return Map.of("users", userService.getAll());
    }

}
