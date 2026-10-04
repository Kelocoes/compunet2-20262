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
public class WebSecurityConfig {
    // @Bean
    // public UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager userDetailsMngr = new
    // InMemoryUserDetailsManager();

    // UserDetails user = User.withUsername("miUsuario")
    // .password("123456")
    // .authorities("READ")
    // .roles("USER")
    // .build();

    // userDetailsMngr.createUser(user);
    // return userDetailsMngr;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ADVERTENCIA: NoOpPasswordEncoder es inseguro y se utiliza únicamente para
        // aprendizaje
        return new BCryptPasswordEncoder();
    }
}
