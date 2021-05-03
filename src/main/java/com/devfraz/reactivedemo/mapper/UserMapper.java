package com.devfraz.reactivedemo.mapper;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto userToUserDto(User user);

  User userDtoToUser(UserDto userDto);
}
