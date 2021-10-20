package io.serviceshark.api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ServiceSharkSecurity extends WebSecurityConfigurerAdapter {
  
  private final String LOGIN_PROCESSING_URL = "/api/auth/login";

  @Autowired
  DataSource dataSource;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    System.out.println("UserDetailsService");
    return new JdbcUserDetailsManager(dataSource);
  }
    
  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    System.out.println("DaoAuthenticationProvider");
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(this.passwordEncoder());
    provider.setUserDetailsService(this.userDetailsService());
    return provider;
  }
    
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin().loginProcessingUrl(this.LOGIN_PROCESSING_URL);
    http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/api/auth/login", "/api/registration/create")
        .permitAll()
        .anyRequest()
        .authenticated();
  }
}
