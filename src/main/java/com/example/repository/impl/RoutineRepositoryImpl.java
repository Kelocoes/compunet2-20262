package com.example.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.example.model.Routine;
import com.example.repository.IRoutineRepository;

public class RoutineRepositoryImpl implements IRoutineRepository {

    private List<Routine> routines = new ArrayList<>();
    private int nextId = 1;
    private Logger logger = Logger.getLogger(RoutineRepositoryImpl.class.getName());

    @Override
    public void init() {
        logger.info("RoutineRepositoryImpl initialized");
        logger.info("Connecting to the routine database...");

        save(new Routine(0, 1, "Rutina Hipertrofia Pecho y Triceps", "Rutina enfocada en fuerza y volumen de empuje", new Date(), new Date()));
        save(new Routine(0, 1, "Rutina Cardio HIIT", "Entrenamiento de alta intensidad para quema de grasa", new Date(), new Date()));
        save(new Routine(0, 2, "Rutina Pierna Completa", "Rutina intensa para cuadriceps, isquios y gemelos", new Date(), new Date()));
    }

    @Override
    public List<Routine> findAll() {
        return new ArrayList<>(routines);
    }

    @Override
    public Optional<Routine> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }

        for (Routine routine : routines) {
            if (id.equals(routine.getId())) {
                return Optional.of(routine);
            }
        }

        return Optional.empty();
    }

    @Override
    public Routine save(Routine routine) {
        if (routine == null) {
            return null;
        }

        if (routine.getId() <= 0) {
            routine.setId(nextId++);
            routines.add(routine);
            return routine;
        }

        for (int i = 0; i < routines.size(); i++) {
            if (routine.getId() == routines.get(i).getId()) {
                routines.set(i, routine);
                return routine;
            }
        }

        routines.add(routine);
        return routine;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        for (int i = 0; i < routines.size(); i++) {
            if (id.equals(routines.get(i).getId())) {
                routines.remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public void destroy() {
        logger.info("RoutineRepositoryImpl destroyed");
        logger.info("Disconnecting from the routine database...");
        routines.clear();
        nextId = 1;
    }
}
