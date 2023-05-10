package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
// инициализация и запуск приложения
@SpringBootApplication
public class ManagerApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
