package com.example.teacher.service.impl;

import com.example.teacher.model.Role;
import com.example.teacher.model.User;
import com.example.teacher.repository.IUserRepository;
import com.example.teacher.service.IRoleService;
import com.example.teacher.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleService roleService;

    @Override
    public User create(User user, Integer roleId) {
        log.info("Creating user: {} with roleId: {}", user, roleId);
        if (roleId != null) {
            Optional<Role> role = roleService.findById(roleId);
            role.ifPresent(user::setRole);
        }
        return userRepository.save(user);
    }

    @Override
    public Page<User> findAll(int page, int size, String sortBy, String direction) {
        log.info("Fetching users page: {}, size: {}, sortBy: {}, direction: {}", page, size, sortBy, direction);
        
        // Determinamos la dirección del ordenamiento de manera segura
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        // Construimos el objeto Sort por la propiedad indicada
        Sort sort = Sort.by(sortDirection, sortBy);

        // Instanciamos el Pageable combinando página, tamaño y orden
        Pageable pageable = PageRequest.of(page, size, sort);

        // Ejecutamos la consulta en el repositorio
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Integer id) {
        log.info("Finding user by id: {}", id);
        return userRepository.findById(id);
    }
}
