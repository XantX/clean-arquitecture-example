package com.example.rest_service.user.infraestructure.controller;

import com.example.rest_service.user.application.usercases.UserUseCase;
import com.example.rest_service.user.domain.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userUseCase.getUser(2);
    }
}
