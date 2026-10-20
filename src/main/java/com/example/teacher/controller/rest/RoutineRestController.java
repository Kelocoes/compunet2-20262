package com.example.teacher.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.teacher.dto.RoutineRequest;
import com.example.teacher.dto.RoutineResponse;
import com.example.teacher.mapper.RoutineMapper;
import com.example.teacher.model.Routine;
import com.example.teacher.service.IRoutineService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/rest/routines")
@RequiredArgsConstructor
public class RoutineRestController {

    private final IRoutineService routineService;
    private final RoutineMapper routineMapper;

    @GetMapping()
    public ResponseEntity<List<RoutineResponse>> find(@RequestParam(required = false) String name) {
        List<Routine> routines = routineService.findAll();

        List<RoutineResponse> routineResponses = routines
                .stream()
                .map(routineMapper::toDto)
                .toList();
        return ResponseEntity.ok().body(routineResponses);
    }

    @PostMapping()
    public ResponseEntity<RoutineResponse> create(@RequestBody RoutineRequest dto) {
        Routine routine = routineMapper.toEntity(dto);
        Routine savedRoutine = routineService.create(routine, dto.getUserId());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(routineMapper.toDto(savedRoutine));
    }

    @PutMapping()
    public String update(@RequestBody String entity) {
        return entity;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        return id.toString();
    }

}
