package com.devfraz.reactivedemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

  @Schema(hidden = true)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Integer id;

  @Schema(description = "User's first name", required = true)
  @NotBlank(message = "First name is required.")
  private String firstName;

  @Schema(description = "User's last name", required = true)
  @NotBlank(message = "Last name is required.")
  private String lastName;

  @Schema(description = "User's email id", required = true)
  @NotBlank(message = "Email is required.")
  private String email;
}
