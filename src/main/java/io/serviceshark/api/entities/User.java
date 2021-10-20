package io.serviceshark.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.serviceshark.api.registration.RegisterUserDto;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
  
  @Id
  @GeneratedValue(
    strategy = GenerationType.AUTO
  )
  private Long id;

  @Column
  private String username;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private boolean enabled;

  public User(RegisterUserDto registerUserDto) {
    this.username = registerUserDto.getUsername();
    this.password = registerUserDto.getPassword();
    this.email = registerUserDto.getEmail();
    this.enabled = true;
  }

  public User() {}
}
