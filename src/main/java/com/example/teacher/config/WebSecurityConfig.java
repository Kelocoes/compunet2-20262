package com.example.teacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity 
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        // Rutas públicas accesibles sin autenticación
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/mvc/public/**").permitAll()
                        .requestMatchers("/mvc/auth/login", "/mvc/auth/register").permitAll()

                        // Rutas restringidas por Rol (Spring busca ROLE_ADMIN automáticamente)
                        .requestMatchers("/mvc/admin/**").hasRole("ADMIN")

                        // Cualquier otra solicitud requiere que el usuario esté autenticado
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/mvc/auth/login") // GET: Controlador que renderiza la vista
                        .loginProcessingUrl("/mvc/auth/login") // POST: Spring Security intercepta el formulario
                        .defaultSuccessUrl("/mvc/users", true) // Redirección ante autenticación exitosa
                        .failureUrl("/mvc/auth/login?error=true") // Redirección ante credenciales inválidas
                        .usernameParameter("username") // Nombre del campo input en el HTML
                        .passwordParameter("password") // Nombre del campo input en el HTML
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/mvc/auth/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .build();
    }
}
