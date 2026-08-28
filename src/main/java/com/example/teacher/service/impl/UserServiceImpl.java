package com.example.teacher.service.impl;

import com.example.teacher.model.Role;
import com.example.teacher.model.User;
import com.example.teacher.repository.IUserRepository;
import com.example.teacher.service.IRoleService;
import com.example.teacher.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleService roleService;

    @Override
    public User create(User user, Integer roleId) {
        log.info("Creating user: {} with roleId: {}", user, roleId);
        if (roleId != null) {
            Optional<Role> role = roleService.findById(roleId);
            role.ifPresent(user::setRole);
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        log.info("Finding user by id: {}", id);
        return userRepository.findById(id);
    }
}
