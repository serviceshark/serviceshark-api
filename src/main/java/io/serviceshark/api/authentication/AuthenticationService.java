package io.serviceshark.api.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.serviceshark.api.entities.User;

@Configuration
public class AuthenticationService implements UserDetailsService {
  
  @Autowired
  private AuthenticationDao authenticationDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("AuthenticationService!");
    User user = this.authenticationDao
        .findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User was not found"));
   
    return user;
  }  
}
