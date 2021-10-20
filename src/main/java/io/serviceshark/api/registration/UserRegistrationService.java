package io.serviceshark.api.registration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.serviceshark.api.entities.User;

@Service
public class UserRegistrationService implements RegistrationService {

  @Autowired
  private RegisterUserDao userDao;

  @Autowired
  private PasswordEncoder encoder;

  @Override
  public void createUser(RegisterUserDto registerUserDto) throws Exception {
    System.out.println("Inside UserRegistrationService.createUser");
    Optional<User> user = this.userDao.findByUsername(registerUserDto.getUsername());
    if (user.isPresent()) {
      throw new Exception("User is found");
    } else {
      registerUserDto.setPassword(
        this.encodePassword(registerUserDto.getPassword()));
      User newUser = new User(registerUserDto);
      this.userDao.save(newUser);
      return;
    }
  }

  protected String encodePassword(String password) {
    return this.encoder.encode(password);
  }
}
