package com.example.teacher.service;

import com.example.teacher.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User create(User user, Integer roleId);
    List<User> findAll();
    Optional<User> findById(Integer id);
}
