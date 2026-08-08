package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Descomentar las siguientes importaciones si se desea usar la configuración basada en métodos @Bean:
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Scope;
// import com.example.repository.IUserRepository;
// import com.example.repository.IRoutineRepository;
// import com.example.repository.impl.UserRepositoryImpl;
// import com.example.repository.impl.RoutineRepositoryImpl;
// import com.example.service.IUserService;
// import com.example.service.impl.UserServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:application.properties")
public class AppConfig {

    // Ejemplo de cómo instanciar beans mediante métodos @Bean en la clase de configuración:

    // @Bean(name = "userRepository", initMethod = "init", destroyMethod = "destroy")
    // @Scope("singleton")
    // public IUserRepository userRepository() {
    //     return new UserRepositoryImpl();
    // }

    // @Bean(name = "routineRepository", initMethod = "init", destroyMethod = "destroy")
    // @Scope("singleton")
    // public IRoutineRepository routineRepository() {
    //     return new RoutineRepositoryImpl();
    // }

    // Wiring can be done via constructor injection or setter injection
    // @Bean(name = "userService")
    // @Scope("singleton")
    // public IUserService userService(IUserRepository userRepository) {
    //     return new UserServiceImpl(userRepository);
    // }
}

