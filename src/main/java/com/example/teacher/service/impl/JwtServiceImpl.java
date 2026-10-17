package com.example.teacher.service.impl;

import com.example.teacher.model.User;
import com.example.teacher.security.CustomUserDetails;
import com.example.teacher.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor 
public class JwtServiceImpl implements IJwtService {

    @Value("${app.security.jwt.secret-key}")
    private String secretKey;

    @Value("${app.security.jwt.expiration-time}")
    private long expirationTime;

    private final UserDetailsService userDetailsService;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(CustomUserDetails customUserDetails) {
        User user = customUserDetails.getUser();
        List<String> authorities = customUserDetails.getAuthorities() != null
                ? customUserDetails.getAuthorities().stream().map(a -> a.getAuthority()).toList()
                : List.of();

        return Jwts.builder()
                .id(user.getId() != null ? user.getId().toString() : null)
                .claims(Map.of(
                        "username", user.getEmail(),
                        "name", user.getName() != null ? user.getName() : "",
                        "email", user.getEmail(),
                        "authorities", authorities
                ))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public String generateToken(User user, Authentication auth) {
        List<String> authorities = (auth != null && auth.getAuthorities() != null)
                ? auth.getAuthorities().stream().map(a -> a.getAuthority()).toList()
                : List.of();

        return Jwts.builder()
                .id(user.getId() != null ? user.getId().toString() : null)
                .claims(Map.of(
                        "username", user.getEmail(),
                        "name", user.getName() != null ? user.getName() : "",
                        "email", user.getEmail(),
                        "authorities", authorities
                ))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claimsResolver.apply(claims);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractClaim(token, Function.identity());
        List<String> authorities = claims.get("authorities", List.class);
        if (authorities == null) {
            return List.of();
        }
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public UserDetails getUserDetailsFromToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token);

            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
