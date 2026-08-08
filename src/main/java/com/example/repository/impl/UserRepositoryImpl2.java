package com.example.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.model.User;
import com.example.repository.IUserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Repository
@Scope("singleton")
public class UserRepositoryImpl2 implements IUserRepository {

    private List<User> users = new ArrayList<>();
    private int nextId = 1;
    private Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    @Override
    @PostConstruct
    public void init() {
        logger.info("UserRepositoryImpl initialized");
        logger.info("Connecting to the user database...");

        save(new User(0, "Valentina Arana Babativa", "va7115755@gmail.com", "secret123", new Date(), new Date()));
        save(new User(0, "Juan David Fajardo Quilindo", "davidfajardo545@gmail.com", "pass456", new Date(), new Date()));
        save(new User(0, "Jhostin Felipe Wiesner Quintero", "jhostinwiesner@gmail.com", "admin789", new Date(), new Date()));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public Optional<User> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (User user : users) {
            if (id.equals(user.getId())) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user == null) {
            return null;
        }

        if (user.getId() <= 0) {
            user.setId(nextId++);
            users.add(user);
            return user;
        }

        for (int i = 0; i < users.size(); i++) {
            if (user.getId() == users.get(i).getId()) {
                users.set(i, user);
                return user;
            }
        }

        users.add(user);
        return user;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        for (int i = 0; i < users.size(); i++) {
            if (id.equals(users.get(i).getId())) {
                users.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    @PreDestroy
    public void destroy() {
        logger.info("UserRepositoryImpl destroyed");
        logger.info("Disconnecting from the user database...");
        users.clear();
        nextId = 1;
    }
}

