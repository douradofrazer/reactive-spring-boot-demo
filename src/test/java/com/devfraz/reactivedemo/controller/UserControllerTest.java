package com.devfraz.reactivedemo.controller;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = UserController.class)
@DisplayName("User Controller Test")
class UserControllerTest {

  @MockBean UserService userService;
  // async client
  @Autowired WebTestClient webClient;

  @Test
  @DisplayName("Valid request should create new user")
  void createUser() {
    UserDto userDto = new UserDto();
    userDto.setFirstName("Robin Van");
    userDto.setLastName("Persie");
    userDto.setEmail("robinvpersie@test.com");

    Mockito.when(userService.createUser(Mockito.any())).thenReturn(Mono.empty());

    webClient
        .post()
        .uri("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(userDto))
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody()
        .isEmpty();
  }

  @Test
  @DisplayName("All users should be returned")
  void getAllUsers() {
    UserDto testUser = new UserDto(1, "JP", "Morgan", "jp.morgan@test.com");

    Mockito.when(userService.listAllUsers()).thenReturn(Flux.just(testUser));

    webClient
        .get()
        .uri("/api/v1/users")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.[0].firstName")
        .isEqualTo(testUser.getFirstName())
        .jsonPath("$.[0].lastName")
        .isEqualTo(testUser.getLastName())
        .jsonPath("$.[0].email")
        .isEqualTo(testUser.getEmail());
  }

  @Test
  @DisplayName("User with the given id should be returned")
  void getUserById() {

    UserDto testUser = new UserDto(1, "JP", "Morgan", "jp.morgan@test.com");

    Mockito.when(userService.getUserById(Mockito.anyInt())).thenReturn(Mono.just(testUser));

    webClient
        .get()
        .uri("/api/v1/users/{userId}", 1)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.firstName")
        .isEqualTo(testUser.getFirstName())
        .jsonPath("$.lastName")
        .isEqualTo(testUser.getLastName())
        .jsonPath("$.email")
        .isEqualTo(testUser.getEmail());
  }
}
