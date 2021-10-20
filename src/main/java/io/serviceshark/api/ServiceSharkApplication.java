package io.serviceshark.api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class ServiceSharkApplication {

    @Autowired
    DataSource dataSource;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     System.out.println("UserDetailsService");
    //     InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
    //     UserDetails user = User.withUsername("anson")
    //         .password(this.passwordEncoder().encode("123"))
    //         .authorities("READ")
    //         .build();
    //     inMemoryUserDetailsManager.createUser(user);
    //     return inMemoryUserDetailsManager;
    // }
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
    public static void main(String[] args) {
        SpringApplication.run(ServiceSharkApplication.class, args);
    }
}
