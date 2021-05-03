package com.devfraz.reactivedemo.service;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.mapper.UserMapper;
import com.devfraz.reactivedemo.model.User;
import com.devfraz.reactivedemo.repository.UserRepository;
import com.devfraz.reactivedemo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Service Test")
class UserServiceImplTest {

  @Mock UserMapper userMapper;

  @Mock UserRepository userRepository;

  @InjectMocks UserServiceImpl userServiceImpl;

  UserDto userDto;

  User user;

  @BeforeEach
  void init() {
    userDto = new UserDto();
    userDto.setFirstName("JP");
    userDto.setLastName("Morgan");
    userDto.setEmail("jp.morgan@test.com");

    user = new User();
    user.setFirstName("JP");
    user.setLastName("Morgan");
    user.setEmail("jp.morgan@test.com");
  }

  @Test
  @DisplayName("Create user should return successfully")
  void createUser() {
    when(userRepository.save(any())).thenReturn(Mono.just(user));
    StepVerifier.create(userServiceImpl.createUser(userDto)).verifyComplete();
  }

  @Test
  @DisplayName("Fetch should return all users")
  void listALlUsers() {
    when(userRepository.findAll()).thenReturn(Flux.just(user));
    when(userMapper.userToUserDto(any())).thenReturn(userDto);
    StepVerifier.create(userServiceImpl.listAllUsers()).expectNext(userDto).verifyComplete();
  }

  @Test
  @DisplayName("Fetch should return user by id")
  void getUserById() {
    when(userRepository.findById(anyInt())).thenReturn(Mono.just(user));
    when(userMapper.userToUserDto(any())).thenReturn(userDto);
    StepVerifier.create(userServiceImpl.getUserById(1)).expectNext(userDto).verifyComplete();
  }
}
