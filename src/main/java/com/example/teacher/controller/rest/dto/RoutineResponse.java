package com.example.teacher.controller.rest.dto;

import com.example.teacher.model.Routine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResponse {

    private Integer id;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer userId;
    private String userName;

    public static RoutineResponse fromEntity(Routine routine) {
        if (routine == null) {
            return null;
        }
        return RoutineResponse.builder()
                .id(routine.getId())
                .name(routine.getName())
                .description(routine.getDescription())
                .createdAt(routine.getCreatedAt())
                .updatedAt(routine.getUpdatedAt())
                .userId(routine.getUser() != null ? routine.getUser().getId() : null)
                .userName(routine.getUser() != null ? routine.getUser().getName() : null)
                .build();
    }
}
