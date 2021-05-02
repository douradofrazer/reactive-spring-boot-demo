package com.devfraz.reactivedemo.service;

import com.devfraz.reactivedemo.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
  Mono<Void> createUser(UserDto userDto);

  Flux<UserDto> listAllUsers();

  Mono<UserDto> getUserById(Integer id);
}
