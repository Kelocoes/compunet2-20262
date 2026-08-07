package com.example.services.impl;

import java.util.List;

import com.example.model.User;
import com.example.repository.IUserRepository;
import com.example.services.IUserService;

public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;

    // public void setUserRepository(IUserRepository userRepository) {
    // this.userRepository = userRepository;
    // }

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String name, String email, String password) {
        // TODO Validate
        User user = new User(0, name, email, password, null, null);
        return userRepository.create(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
