package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.example","controller","service","dao","dto","config","util","filter" })
@EnableAutoConfiguration
@SpringBootApplication
public class Cmpe275ProjectManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cmpe275ProjectManagementApplication.class, args);
    }
}
