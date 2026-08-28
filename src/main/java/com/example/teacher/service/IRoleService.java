package com.example.teacher.service;

import com.example.teacher.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findById(Integer id);
}
