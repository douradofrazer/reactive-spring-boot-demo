package com.devfraz.reactivedemo.service.impl;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.mapper.UserMapper;
import com.devfraz.reactivedemo.repository.UserRepository;
import com.devfraz.reactivedemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  @Override
  public Mono<Void> createUser(UserDto userDto) {
    var user = userMapper.userDtoToUser(userDto);
    return userRepository.save(user).log("Saving user to db..").then();
  }

  @Override
  public Flux<UserDto> listAllUsers() {
    return userRepository
        .findAll()
        .log("Fetched all users from db")
        .map(userMapper::userToUserDto)
        .log("Mapped users to userDto");
  }

  @Override
  public Mono<UserDto> getUserById(Integer id) {
    return userRepository
        .findById(id)
        .log("Fetched the user from db")
        .map(userMapper::userToUserDto)
        .log("Mapped user to userDto");
  }
}
