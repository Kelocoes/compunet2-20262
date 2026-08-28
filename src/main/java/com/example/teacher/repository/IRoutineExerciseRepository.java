package com.example.teacher.repository;

import com.example.teacher.model.RoutineExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoutineExerciseRepository extends JpaRepository<RoutineExercise, Integer> {
}
