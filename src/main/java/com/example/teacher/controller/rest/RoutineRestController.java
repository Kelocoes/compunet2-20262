package com.example.teacher.controller.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.teacher.dto.RoutineResponse;
import com.example.teacher.model.Routine;
import com.example.teacher.service.IRoutineService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("routines")
@RequiredArgsConstructor
public class RoutineRestController {

    private final IRoutineService routineService;

    @GetMapping()
    public List<RoutineResponse> find(@RequestParam(required = false) String name) {
        List<Routine> routines = routineService.findAll();

        List<RoutineResponse> routineResponses = routines
        .stream()
        .map(RoutineResponse::toDto)
        .toList();
        return routineResponses;
    }

    @PostMapping()
    public String create(@RequestBody String entity) {
        return entity;
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
