package com.example.service;

import java.util.List;

import com.example.model.User;

public interface IUserService {
    List<User> findAll();
    User findById(Integer id);
    User save(User user);
    boolean delete(Integer id);
}
