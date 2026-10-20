package com.example.teacher.dto;

import com.example.teacher.model.Routine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoutineResponse {

    private Integer id;

    private String name;

    private String description;

    public static RoutineResponse toDto(Routine routine) {
        return RoutineResponse.builder()
                .id(routine.getId())
                .name(routine.getName())
                .description(routine.getDescription())
                .build();
    }
}
