package com.example.teacher.controller;

import com.example.teacher.model.User;
import com.example.teacher.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> findAll() {
        int page = 0;
        int size = 10;
        String sortBy = "id";
        String direction = "asc";

        Page<User> userPage = userService.findAll(page, size, sortBy, direction);
        return ResponseEntity.ok(userPage);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id).orElse(null);
    }

    @GetMapping("/save")
    public User save() {
        Integer roleId = 1;
        User newUser = User.builder()
                .name("Username")
                .email("email-" + UUID.randomUUID() + "@gmail.com")
                .password("123456")
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        return userService.create(newUser, roleId);
    }

    @GetMapping("/test-transaction")
    public ResponseEntity<?> testTransaction() throws com.example.teacher.exceptions.TransactionTestException {
        // Creamos una lista de usuarios falsos cortos dentro del cuerpo del método
        List<User> fakeUsers = List.of(
                User.builder()
                        .name("Usuario Valido 1")
                        .email("user1-" + UUID.randomUUID() + "@gmail.com")
                        .password("123456")
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build(),
                User.builder()
                        .name("Usuario Valido 2")
                        .email("user2-" + UUID.randomUUID() + "@gmail.com")
                        .password("123456")
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build(),
                User.builder()
                        .name("Usuario Que Falla")
                        .email("error-" + UUID.randomUUID() + "@gmail.com") // Este email dispara TransactionTestException
                        .password("123456")
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .build()
        );

        List<User> savedUsers = userService.saveAllUsers(fakeUsers);
        return ResponseEntity.ok(savedUsers);
    }
}
