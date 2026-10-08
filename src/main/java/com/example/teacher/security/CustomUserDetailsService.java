package com.example.teacher.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.teacher.model.User;
import com.example.teacher.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    @Override // Especificar cómo Spring Security va a encontrar el usuario dentro de nuestra
              // BD
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userService.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new CustomUserDetails(user.get());
    }

}
