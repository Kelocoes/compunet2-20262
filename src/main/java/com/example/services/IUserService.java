package com.example.services;

import java.util.List;

import com.example.model.User;

public interface IUserService {
    User create(String name, String email, String password);

    List<User> findAll();
}
