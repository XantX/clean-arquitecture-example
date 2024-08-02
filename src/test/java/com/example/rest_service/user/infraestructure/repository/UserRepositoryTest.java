package com.example.rest_service.user.infraestructure.repository;

import com.example.rest_service.user.domain.entities.User;
import com.example.rest_service.user.infraestructure.schemas.UserJsonSerializable;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private UserRepository userRepository;

    @Test
    public void getUsers() {
        //Arrange
        int limit = 2;
        UserJsonSerializable.UserJsonApi user1 = new UserJsonSerializable.UserJsonApi();
        user1.setEmail("user1@example.com");
        user1.setGender("male");
        UserJsonSerializable.NameJson name1 = new UserJsonSerializable.NameJson();
        name1.setFirst("User1");
        name1.setLast("One");
        UserJsonSerializable.Login login1 = new UserJsonSerializable.Login();
        login1.setUuid(UUID.randomUUID().toString());

        user1.setLogin(login1);
        user1.setName(name1);

        UserJsonSerializable.UserJsonApi user2 = new UserJsonSerializable.UserJsonApi();
        user2.setEmail("user2@example.com");
        user2.setGender("female");
        UserJsonSerializable.NameJson name2 = new UserJsonSerializable.NameJson();
        name2.setFirst("User2");
        name2.setLast("Two");
        UserJsonSerializable.Login login2 = new UserJsonSerializable.Login();
        login2.setUuid(UUID.randomUUID().toString());

        user2.setName(name2);
        user2.setLogin(login2);

        String baseUrl = "https://randomuser.me/api";

        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("results", limit)
                .toUriString();
        UserJsonSerializable expectedUsers = new UserJsonSerializable();
        expectedUsers.setResults(Arrays.asList(user1, user2));
        when(restTemplate.getForObject(url, UserJsonSerializable.class)).thenReturn(expectedUsers);
        //Act
        List<User> users = userRepository.getUsers(limit);
        //Assert
        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(users).hasSize(2);
        Assertions.assertThat(users.get(0).getName()).isEqualTo("User1");
        Assertions.assertThat(users.get(1).getName()).isEqualTo("User2");
    }
}