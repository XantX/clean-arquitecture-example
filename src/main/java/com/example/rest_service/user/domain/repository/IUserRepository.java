package com.example.rest_service.user.domain.repository;

import com.example.rest_service.user.domain.entities.User;

import java.util.List;

public interface IUserRepository {
    public List<User> getUsers(int limit);
}
