package io.serviceshark.api.registration;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
  
  @Email(message = "Must be a valid Email")
  @NotEmpty(message = "Email is required")
  private String email;

  @NotEmpty(message = "Username is required")
  private String username;

  @NotEmpty(message = "Password is required")
  private String password;

  @NotEmpty(message = "Must confirm password")
  private String confirm;
}
