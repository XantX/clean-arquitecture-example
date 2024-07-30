package com.example.rest_service.user.application.usercases;

import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.domain.repository.IUserRepository;

import java.util.List;

public class UserUseCase {
    private final IUserRepository userRepository;

    public UserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUser(int limit) {
        return userRepository.getUsers(limit);
    }
}
