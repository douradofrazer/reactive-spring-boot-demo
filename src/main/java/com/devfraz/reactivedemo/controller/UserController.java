package com.devfraz.reactivedemo.controller;

import com.devfraz.reactivedemo.dto.UserDto;
import com.devfraz.reactivedemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "User APIs")
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @Operation(
      summary = "Create a user.",
      method = "POST",
      responses = {
        @ApiResponse(
            description = "Successful Operation",
            responseCode = "201",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication Failure",
            content = @Content(schema = @Schema(hidden = true)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> createUser(@RequestBody @Validated UserDto userDto) {
    return userService.createUser(userDto);
  }

  @Operation(
      summary = "List all users.",
      method = "GET",
      responses = {
        @ApiResponse(
            description = "Successful Operation",
            responseCode = "200",
            content =
                @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication Failure",
            content = @Content(schema = @Schema(hidden = true)))
      })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Flux<UserDto> listAllUsers() {
    return userService.listAllUsers();
  }

  @Operation(
      summary = "Get a user by ID.",
      method = "GET",
      responses = {
        @ApiResponse(
            description = "Successful Operation",
            responseCode = "200",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication Failure",
            content = @Content(schema = @Schema(hidden = true)))
      })
  @GetMapping(value = "/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<UserDto> getUserById(@PathVariable(value = "userId") Integer userId) {
    return userService.getUserById(userId);
  }
}
