package com.example.teacher.service.impl;

import com.example.teacher.controller.rest.dto.LoginRequestDTO;
import com.example.teacher.controller.rest.dto.TokenResponseDTO;
import com.example.teacher.security.CustomUserDetails;
import com.example.teacher.service.IAuthService;
import com.example.teacher.service.IJwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDTO login(LoginRequestDTO request) {
        // 1. Cargar el usuario mediante UserDetailsService (obteniendo CustomUserDetails)
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("Credenciales inválidas: usuario no encontrado");
        }

        // 2. Verificar que la contraseña coincida con el hash BCrypt
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas: contraseña incorrecta");
        }

        // 3. Castear a nuestro CustomUserDetails
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;

        // 4. Emitir el token firmado pasando directamente el objeto CustomUserDetails
        String token = jwtService.generateToken(customUserDetails);
        return new TokenResponseDTO(token);
    }
}
