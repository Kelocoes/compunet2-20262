package com.example.teacher.repository;

import com.example.teacher.model.ActivityExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityExerciseRepository extends JpaRepository<ActivityExercise, Integer> {
}
