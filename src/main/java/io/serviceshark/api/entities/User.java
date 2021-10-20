package io.serviceshark.api.entities;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.serviceshark.api.registration.RegisterUserDto;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User implements UserDetails {
  
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

  @Column
  private String authority;

  public User(RegisterUserDto registerUserDto) {
    this.username = registerUserDto.getUsername();
    this.password = registerUserDto.getPassword();
    this.email = registerUserDto.getEmail();
    this.enabled = true;
  }

  public User() {}

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
    return Collections.singletonList(authority);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
