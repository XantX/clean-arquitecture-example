package com.example.rest_service.user.infraestructure.config;

import com.example.rest_service.user.application.usercases.UserUseCase;
import com.example.rest_service.user.infraestructure.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserUseCase userUseCase() {
        RestTemplate restTemplate = new RestTemplate();
        return new UserUseCase(new UserRepository(restTemplate));
    }
}
