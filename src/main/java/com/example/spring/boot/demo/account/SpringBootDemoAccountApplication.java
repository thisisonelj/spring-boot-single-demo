package com.example.spring.boot.demo.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.example.spring.boot.demo.account.dao")
@SpringBootApplication
public class SpringBootDemoAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoAccountApplication.class, args);
    }

}
