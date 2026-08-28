package com.example.teacher.repository;

import com.example.teacher.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolePermissionRepository extends JpaRepository<RolePermission, Integer> {
}
