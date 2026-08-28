package com.example.teacher.repository;

import com.example.teacher.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {
}
