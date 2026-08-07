package com.example.repository.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.model.User;
import com.example.repository.IUserRepository;

public class UserRepositoryImpl implements IUserRepository {

    private List<User> users = new ArrayList<>();
    private int nextId = 1;
    private Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    public void init() {
        logger.info("Bean inicializandose");
    }

    @Override
    public void destroy() {
        logger.info("Bean a punto de destruirse!");
    }

    @Override
    public User create(User user) {
        if (user == null) {
            return null;
        }
        user.setId(nextId);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        nextId++;
        users.add(user);

        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

}
