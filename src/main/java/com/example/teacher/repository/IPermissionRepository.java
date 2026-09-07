package com.example.teacher.repository;

import com.example.teacher.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IPermissionRepository extends JpaRepository<Permission, Integer> {

    // Query method con JOIN para obtener los permisos de un usuario por su ID
    List<Permission> findByRolePermissions_Role_Users_Id(Integer userId);
}
