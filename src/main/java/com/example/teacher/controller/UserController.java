package com.example.teacher.controller;

import com.example.teacher.model.User;
import com.example.teacher.service.IUserService;
import lombok.RequiredArgsConstructor;
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
    public List<User> findAll() {
        return userService.findAll();
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
}
