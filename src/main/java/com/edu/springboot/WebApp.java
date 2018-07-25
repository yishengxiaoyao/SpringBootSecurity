package com.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.edu")
public class WebApp {
    public static void main(String[] args){
        SpringApplication.run(WebApp.class,args);
    }
}
