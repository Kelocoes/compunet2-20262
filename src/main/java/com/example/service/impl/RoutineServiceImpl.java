package com.example.service.impl;

import java.util.List;

import com.example.model.Routine;
import com.example.repository.IRoutineRepository;
import com.example.service.IRoutineService;

public class RoutineServiceImpl implements IRoutineService {

    private IRoutineRepository routineRepository;

    // Constructor por defecto - necesario para inyección por setter
    public RoutineServiceImpl() {
    }

    // Constructor con argumento - para inyección por constructor
    public RoutineServiceImpl(IRoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    // Setter - para inyección por setter
    public void setRoutineRepository(IRoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public List<Routine> findAll() {
        return routineRepository.findAll();
    }

    @Override
    public Routine findById(Integer id) {
        return routineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Routine not found with id: " + id));
    }

    @Override
    public Routine save(Routine routine) {
        return routineRepository.save(routine);
    }

    @Override
    public boolean delete(Integer id) {
        return routineRepository.delete(id);
    }
}
