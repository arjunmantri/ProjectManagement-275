package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.example","edu.sjsu.cmpe275.controller","edu.sjsu.cmpe275.dao.impl",
		"edu.sjsu.cmpe275.dto","config","util","filter" })
@EnableAutoConfiguration
@SpringBootApplication
public class Cmpe275ProjectManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cmpe275ProjectManagementApplication.class, args);
    }
}
