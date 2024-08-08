package com.example.rest_service.user.infraestructure.controller;

import com.example.rest_service.user.application.usercases.UserUseCase;
import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.infraestructure.controller.dtos.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = UserController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserUseCase userUseCase;

    @MockBean
    private RestTemplateBuilder restTemplateBuilder;

    private List<User> users;
    private List<UserDto> userDtos;

    @BeforeEach
    public void init() {
        users = Arrays.asList(buildUser("Pedro", "Palotes"), buildUser("Pepa", "Pig"));
        userDtos = users.stream().map(user -> new UserDto(user.getName(), user.getLastname())).toList();
        RestTemplate restTemplate = new RestTemplate();
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    public User buildUser(String name, String lastname) {
        return new User(UUID.randomUUID().toString(), name, lastname);
    }

    @Test
    public void getUsers() throws Exception {
        when(userUseCase.getUser(users.size())).thenReturn(users);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .queryParam("limit", String.valueOf(users.size()))
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Pedro"));
    }
}