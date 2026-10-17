package com.example.teacher.security;

import com.example.teacher.service.IJwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final IJwtService jwtService;

    public JwtAuthenticationFilter(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtener la cabecera HTTP 'Authorization'
        String authHeader = request.getHeader("Authorization");
        String token = null;

        // 2. Verificar que la cabecera exista y comience con el prefijo estándar 'Bearer '
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            // 3. Validar la firma y vigencia del token
            if (jwtService.isTokenValid(token)) {
                // 4. Reconstruir los detalles del usuario a partir del Payload del JWT
                UserDetails userDetails = jwtService.getUserDetailsFromToken(token);

                // 5. Crear el objeto de autenticación de Spring Security
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Adjuntar metadatos de la solicitud (IP, sesión web)
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. Almacenar el usuario autenticado en el contexto del hilo actual
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 7. Continuar con el siguiente filtro en la cadena de Spring Security
        filterChain.doFilter(request, response);
    }
}
