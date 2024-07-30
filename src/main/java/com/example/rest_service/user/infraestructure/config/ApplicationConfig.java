package com.example.rest_service.user.infraestructure.config;

import com.example.rest_service.user.application.usercases.UserUseCase;
import com.example.rest_service.user.infraestructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserUseCase userUseCase() {
        return new UserUseCase(new UserRepository());
    }
}
