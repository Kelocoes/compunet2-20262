package com.example.teacher.repository;

import com.example.teacher.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseRepository extends JpaRepository<Exercise, Integer> {
}
