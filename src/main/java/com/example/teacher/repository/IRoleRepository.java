package com.example.teacher.repository;

import com.example.teacher.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    // Query method con JOIN implícito (Role -> User)
    List<Role> findByUsers_Name(String name);

    // Consulta solicitada con JPQL
    @Query("SELECT r FROM Role r WHERE SIZE(r.users) >= 1")
    List<Role> findByRolesWithAtLeast1Username();
}
