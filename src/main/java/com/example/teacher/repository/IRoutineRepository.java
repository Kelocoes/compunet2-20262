package com.example.teacher.repository;

import com.example.teacher.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoutineRepository extends JpaRepository<Routine, Integer> {
}
