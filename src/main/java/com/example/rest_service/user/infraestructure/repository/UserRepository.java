package com.example.rest_service.user.infraestructure.repository;

import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.domain.repository.IUserRepository;
import com.example.rest_service.user.infraestructure.schemas.UserJsonSerializable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class UserRepository implements IUserRepository {
    private final RestTemplate restTemplate;
    private final String basePath = "https://randomuser.me/api";

    public UserRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(int limit) {
        String url = UriComponentsBuilder.fromHttpUrl(basePath)
                .queryParam("results", limit)
                .toUriString();
        UserJsonSerializable response
                = restTemplate.getForObject(url, UserJsonSerializable.class);
        assert response != null;
        return response.getResults().stream().map(
                data -> new User(data.getLogin().getUuid(),data.getName().getFirst(), data.getName().getLast())
        ).toList();
    }
}
