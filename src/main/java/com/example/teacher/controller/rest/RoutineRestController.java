package com.example.teacher.controller.rest;

import com.example.teacher.controller.rest.dto.RoutineRequest;
import com.example.teacher.controller.rest.dto.RoutineResponse;
import com.example.teacher.exceptions.ResourceNotFoundException;
import com.example.teacher.model.Routine;
import com.example.teacher.service.IRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/routines")
@RequiredArgsConstructor
public class RoutineRestController {

    private final IRoutineService routineService;

    @GetMapping
    public ResponseEntity<List<RoutineResponse>> getAllRoutines(
            @RequestParam(required = false) String search) {

        List<Routine> routines;
        if (search != null && !search.trim().isEmpty()) {
            routines = routineService.searchByName(search.trim());
        } else {
            routines = routineService.findAll();
        }

        List<RoutineResponse> response = routines.stream()
                .map(RoutineResponse::fromEntity)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutineResponse> getRoutineById(@PathVariable Integer id) {
        Routine routine = routineService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + id));

        return ResponseEntity.status(HttpStatus.OK).body(RoutineResponse.fromEntity(routine));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoutineResponse>> getRoutinesByUser(@PathVariable Integer userId) {
        List<RoutineResponse> response = routineService.findByUserId(userId)
                .stream()
                .map(RoutineResponse::fromEntity)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(@RequestBody RoutineRequest request) {
        Routine routine = Routine.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Routine created = routineService.create(routine, request.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(RoutineResponse.fromEntity(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutineResponse> updateRoutine(
            @PathVariable Integer id,
            @RequestBody RoutineRequest request) {

        Routine routine = Routine.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Routine updated = routineService.update(id, routine, request.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(RoutineResponse.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Integer id) {
        routineService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
