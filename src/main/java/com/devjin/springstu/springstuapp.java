package com.devjin.springstu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
public class springstuapp {
    public static void main(String[] args) {
        SpringApplication.run(springstuapp.class, args);
    }
}