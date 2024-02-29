package com.example.taskmanager.config;


import com.example.taskmanager.model.MyPersonFactory;
import com.example.taskmanager.model.MyTaskFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyPersonFactory productFactory(){
        return new MyPersonFactory();
    }
    @Bean
    public MyTaskFactory taskFactory(){
        return new MyTaskFactory();
    }
}
