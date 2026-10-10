package com.example.teacher.service;

import com.example.teacher.model.Routine;

import java.util.List;
import java.util.Optional;

public interface IRoutineService {
    Routine create(Routine routine, Integer userId);
    Routine create(Routine routine);
    List<Routine> findAll();
    List<Routine> findByUserId(Integer userId);
    List<Routine> searchByName(String name);
    Optional<Routine> findById(Integer id);
    Routine update(Integer id, Routine routine, Integer userId);
    void deleteById(Integer id);
}
