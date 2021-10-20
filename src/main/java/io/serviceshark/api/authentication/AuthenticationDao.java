package io.serviceshark.api.authentication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.serviceshark.api.entities.User;

public interface AuthenticationDao extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
