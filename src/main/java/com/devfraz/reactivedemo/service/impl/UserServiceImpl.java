package com.devfraz.reactivedemo.service.impl;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.model.User;
import com.devfraz.reactivedemo.repository.UserRepository;
import com.devfraz.reactivedemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Mono<Void> createUser(UserDto userDto) {
    var user = new User();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setEmail(userDto.getEmail());
    return userRepository.save(user).then();
  }

  @Override
  public Flux<UserDto> listAllUsers() {
    return userRepository
        .findAll()
        .map(
            user ->
                new UserDto(
                    user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
  }

  @Override
  public Mono<UserDto> getUserById(Integer id) {
    return userRepository
        .findById(id)
        .map(
            user ->
                new UserDto(
                    user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
  }
}
