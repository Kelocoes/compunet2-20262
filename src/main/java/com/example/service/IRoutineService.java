package com.example.service;

import java.util.List;

import com.example.model.Routine;

public interface IRoutineService {
    List<Routine> findAll();
    Routine findById(Integer id);
    Routine save(Routine routine);
    boolean delete(Integer id);
}
