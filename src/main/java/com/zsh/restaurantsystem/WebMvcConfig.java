package com.zsh.restaurantsystem;

import com.zsh.restaurantsystem.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private User1Interceptor user1Interceptor;
    @Autowired
    private User2Interceptor user2Interceptor;
    @Autowired
    private WeChatInterceptor weChatInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/api/admin/**")
//                .excludePathPatterns("/api/login");
//        registry.addInterceptor(adminInterceptor)
//                .addPathPatterns("/api/admin");
//        registry.addInterceptor(user1Interceptor)
//                .addPathPatterns("/api/admin");
//        registry.addInterceptor(user2Interceptor)
//                .addPathPatterns("/api/admin");
        registry.addInterceptor(weChatInterceptor)
                .addPathPatterns("/api/wechat/**")
                .excludePathPatterns("/api/wechat/login")
                .excludePathPatterns("/api/wechat/show/**");
    }
}
