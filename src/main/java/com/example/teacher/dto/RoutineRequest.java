package com.example.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor
@NoArgsConstructor 
public class RoutineRequest {
    
    private String name;
    private String description;
    private Integer userId;
}
