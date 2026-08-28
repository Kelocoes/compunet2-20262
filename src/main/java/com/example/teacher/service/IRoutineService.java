package com.example.teacher.service;

import com.example.teacher.model.Routine;

import java.util.List;
import java.util.Optional;

public interface IRoutineService {
    Routine create(Routine routine);
    List<Routine> findAll();
    Optional<Routine> findById(Integer id);
}
