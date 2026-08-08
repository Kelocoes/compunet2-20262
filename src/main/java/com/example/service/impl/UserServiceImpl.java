package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.IUserRepository;
import com.example.service.IUserService;

@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    // Constructor por defecto - necesario para inyección por setter
    public UserServiceImpl() {
    }

    // Constructor con argumento - para inyección por constructor
    @Autowired
    public UserServiceImpl(@Qualifier("userRepositoryImpl") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Setter - para inyección por setter
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userRepository.delete(id);
    }
}

