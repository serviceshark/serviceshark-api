package io.serviceshark.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ServiceSharkSecurity extends WebSecurityConfigurerAdapter {
  
  private final String LOGIN_PROCESSING_URL = "/api/auth/login";
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
