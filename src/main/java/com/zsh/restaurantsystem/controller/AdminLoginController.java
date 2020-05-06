package com.zsh.restaurantsystem.controller;

import com.zsh.restaurantsystem.component.EncryptorComponent;
import com.zsh.restaurantsystem.entity.Admin;
import com.zsh.restaurantsystem.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {
    //前台
    private static final String USER1_ROLE = "bb63e5f7e0f2ffae845c";
    //管理权限
    private static final String ADMIN_ROLE = "6983f953b49c88210cb9";
    //后厨
    private static final String USER2_ROLE = "783f956f147597a7d921";
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptorComponent encryptorComponent;

    @PostMapping("/login")
    public void login(@RequestBody Admin admin, HttpServletResponse response) {
        Optional.ofNullable(adminService.getAdmin(admin.getName()))
                .ifPresentOrElse(u -> {
                    if (!passwordEncoder.matches(admin.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    // 生成加密token
                    String token = encryptorComponent.encrypt(map);
                    // 在header创建自定义的权限
                    response.setHeader("token", token);
                    String role = null;
                    if (u.getAuthority() == Admin.USER1_AUTHORITY) {
                        role = USER1_ROLE;
                    }
                    if (u.getAuthority() == Admin.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }
                    if (u.getAuthority() == Admin.USER2_AUTHORITY) {
                        role =USER2_ROLE;
                    }
                    response.setHeader("role", role);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });
    }
}
