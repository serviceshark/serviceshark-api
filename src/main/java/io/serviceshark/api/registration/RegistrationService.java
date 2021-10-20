package io.serviceshark.api.registration;

public interface RegistrationService {
  void createUser(RegisterUserDto registerUserDto) throws Exception;
}
