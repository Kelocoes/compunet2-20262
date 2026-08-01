package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.User;

public interface IUserRepository {
    void init();
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User user);
    boolean delete(Integer id);
    void destroy();
}
