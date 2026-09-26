package com.example.teacher.service;

import com.example.teacher.model.Exercise;

import java.util.List;
import java.util.Optional;

public interface IExerciseService {
    Exercise create(Exercise exercise);
    List<Exercise> findAll();
    Optional<Exercise> findById(Integer id);
    Exercise update(Integer id, Exercise exercise);
    void deleteById(Integer id);
}
