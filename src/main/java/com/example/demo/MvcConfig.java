package com.example.demo;

// конфигурационный класс для объединени всех методов в одно Spring boot приложение
// все зависимости будут взаимодействовать во всех классах
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// MvcConfig используется для конфигурации Spring MVC (Model-View-Controller)
// в приложении, которое использует Spring Boot
@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
    }
}
