package com.example.teacher.service.impl;

import com.example.teacher.model.Routine;
import com.example.teacher.repository.IRoutineRepository;
import com.example.teacher.service.IRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements IRoutineService {

    private final IRoutineRepository routineRepository;

    @Override
    public Routine create(Routine routine) {
        log.info("Creating routine: {}", routine);
        return routineRepository.save(routine);
    }

    @Override
    public List<Routine> findAll() {
        log.info("Fetching all routines");
        return routineRepository.findAll();
    }

    @Override
    public Optional<Routine> findById(Integer id) {
        log.info("Finding routine by id: {}", id);
        return routineRepository.findById(id);
    }
}
