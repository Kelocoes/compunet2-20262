package com.example.repository;

import java.util.List;

import com.example.model.User;

public interface IUserRepository {
    void init();

    void destroy();

    User create(User user);

    List<User> findAll();
}
