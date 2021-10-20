package io.serviceshark.api.authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AuthenticationController")
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
  @GetMapping("status")
  public String getStatus() {
    return "{\"status\": \"OK\"}";
  }
  
}
