package com.example.teacher.service.impl;

import com.example.teacher.model.Role;
import com.example.teacher.repository.IRoleRepository;
import com.example.teacher.service.IRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public Optional<Role> findById(Integer id) {
        log.info("Finding role by id: {}", id);
        return roleRepository.findById(id);
    }
}
