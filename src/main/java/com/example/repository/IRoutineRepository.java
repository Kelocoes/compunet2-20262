package com.example.repository;

import java.util.List;

import com.example.model.Routine;

public interface IRoutineRepository {
    Routine create(Routine routine);

    List<Routine> findAll();
}
