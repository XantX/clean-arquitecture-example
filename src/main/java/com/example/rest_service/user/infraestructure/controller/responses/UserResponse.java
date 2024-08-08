package com.example.rest_service.user.infraestructure.controller.responses;

import com.example.rest_service.user.infraestructure.controller.dtos.UserDto;

import java.util.List;

public class UserResponse {
    private List<UserDto> data;
    private String message;
    private int status;

    public UserResponse() {

    }
    public UserResponse(List<UserDto> users, String message, int status) {
        this.data = users;
        this.message = message;
        this.status = status;
    }

    public List<UserDto> getData() {
        return data;
    }

    public void setData(List<UserDto> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
