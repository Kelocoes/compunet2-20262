package com.example.teacher.repository;

import com.example.teacher.model.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
}
