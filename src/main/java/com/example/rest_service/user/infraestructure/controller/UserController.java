package com.example.rest_service.user.infraestructure.controller;

import com.example.rest_service.user.application.usercases.UserUseCase;
import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.infraestructure.controller.dtos.UserDto;
import com.example.rest_service.user.infraestructure.controller.responses.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUsers(@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        List<User> users = userUseCase.getUser(limit);
        List<UserDto> userDtos = users.stream().map(user -> new UserDto(user.getName(), user.getLastname())).toList();
        return new ResponseEntity<UserResponse>(new UserResponse(userDtos, "Bien", HttpStatus.OK.value()), HttpStatus.OK);
    }
}
