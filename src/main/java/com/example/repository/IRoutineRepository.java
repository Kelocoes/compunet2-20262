package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.Routine;

public interface IRoutineRepository {
    void init();
    List<Routine> findAll();
    Optional<Routine> findById(Integer id);
    Routine save(Routine routine);
    boolean delete(Integer id);
    void destroy();
}
