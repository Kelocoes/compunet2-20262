package com.example.teacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebAppConfig {

    // @Bean
    // public UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager userDetailsManger = new
    // InMemoryUserDetailsManager();

    // // UserDetails
    // UserDetails user = User.withUsername("kelocoes")
    // .password("123456")
    // .roles("USER")
    // .authorities("READ_USERS")
    // .build();

    // userDetailsManger.createUser(user);
    // return userDetailsManger;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance()
    }
}