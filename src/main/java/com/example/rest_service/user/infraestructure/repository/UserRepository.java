package com.example.rest_service.user.infraestructure.repository;

import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.domain.repository.IUserRepository;
import com.example.rest_service.user.infraestructure.schemas.UserJsonSerializable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository implements IUserRepository {
    @Override
    public List<User> getUsers(int limit) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "https://randomuser.me/api?results="+limit;
        UserJsonSerializable response
                = restTemplate.getForObject(fooResourceUrl, UserJsonSerializable.class);

        assert response != null;
        return response.getResults().stream().map(
                data -> new User(data.getLogin().getUuid(),data.getName().getFirst(), data.getName().getLast())
        ).toList();
    }
}
