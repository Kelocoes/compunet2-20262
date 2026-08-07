package com.example.repository.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Routine;
import com.example.repository.IRoutineRepository;

public class RoutineRepositoryImpl implements IRoutineRepository {

    private List<Routine> routines = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Routine create(Routine routine) {
        if (routine == null) {
            return null;
        }
        routine.setId(nextId);
        routine.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        nextId++;
        routines.add(routine);

        return routine;
    }

    @Override
    public List<Routine> findAll() {
        return routines;
    }

}
