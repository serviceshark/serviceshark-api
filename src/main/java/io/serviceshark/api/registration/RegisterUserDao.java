package io.serviceshark.api.registration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.serviceshark.api.entities.User;

public interface RegisterUserDao extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  
}
