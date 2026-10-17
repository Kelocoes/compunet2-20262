package com.example.teacher.controller.rest.dto;

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
    private UserResponse user;
}
