package com.example.spring.boot.demo.account.config.power;

import com.example.spring.boot.demo.account.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author liujun
 * @Date 2023/8/10 17:06
 * @Description
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(userLoginInterceptor).addPathPatterns("/rest/account/user/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/rest/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST")
                .maxAge(3600);
        // 可以继续添加其他URL规则:
        // registry.addMapping("/rest/v2/**")...
    }
}
