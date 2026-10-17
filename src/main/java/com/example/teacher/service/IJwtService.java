package com.example.teacher.service;

import com.example.teacher.model.User;
import com.example.teacher.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.function.Function;

public interface IJwtService {

    // Genera un token firmado a partir de CustomUserDetails
    String generateToken(CustomUserDetails customUserDetails);

    // Genera un token firmado a partir de la entidad User y la autenticación
    String generateToken(User user, Authentication authentication);

    // Extrae el username (email) del token
    String extractUsername(String token);

    // Extrae los roles y permisos almacenados en el payload
    List<SimpleGrantedAuthority> extractAuthorities(String token);

    // Extrae un claim genérico utilizando una función resolutora
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    // Reconstruye el UserDetails para inyectar en el contexto de seguridad
    UserDetails getUserDetailsFromToken(String token);

    // Comprueba si el token ya superó su fecha límite de validez
    boolean isTokenExpired(String token);

    // Valida criptográficamente la integridad y expiración del token
    boolean isTokenValid(String token);
}
