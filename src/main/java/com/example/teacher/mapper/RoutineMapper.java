package com.example.teacher.mapper;

import org.mapstruct.Mapper;

import com.example.teacher.dto.RoutineRequest;
import com.example.teacher.dto.RoutineResponse;
import com.example.teacher.model.Routine;

@Mapper(componentModel = "spring")
public interface RoutineMapper {

    Routine toEntity(RoutineRequest request);
    RoutineResponse toDto(Routine routine);
}
