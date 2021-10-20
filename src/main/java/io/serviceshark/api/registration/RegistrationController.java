package io.serviceshark.api.registration;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("RegistrationController")
@RequestMapping(path = "/api/registration")
public class RegistrationController {

  @Autowired
  private UserRegistrationService userRegistrationService;
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @PostMapping(
    value = "/create",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
    try {
      this.userRegistrationService.createUser(registerUserDto);
      return ResponseEntity.status(HttpStatus.OK).body("Good");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      return ResponseEntity.badRequest().build();
    }
  }
}
