package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.service.AdminService;
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
    PasswordEncoder passwordEncoder;

    @PostMapping("/select")
    public Map SelectAdmin(@RequestBody Admin admin,HttpServletResponse response) {
        return Map.of("admin",adminService.getAdmin(admin.getName())) ;
    }

    @PostMapping("/add")
    public void AddAdmin(@RequestBody Admin admin, HttpServletResponse response) {
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
    }

    @GetMapping("/select_all")
    public Map getAllAdmin(){
        return Map.of("admins",adminService.getAllAdmin());
    }

    @PostMapping("/delete")
    public void deleteAdmin(@RequestBody Admin admin,HttpServletResponse response){
        adminService.DeleteAdmin(admin.getId());
    }

    @PostMapping("/update")
    public void updateAdmin(@RequestBody Admin admin,HttpServletResponse response){
        String pwd = "";
        if(admin.getPassword().equals(""))
            ;
        else
        pwd = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(pwd);
        adminService.UpdateAdmin(admin);
    }

}
