package com.example.teacher.mappers;

import com.example.teacher.controller.rest.dto.RoutineRequest;
import com.example.teacher.controller.rest.dto.RoutineResponse;
import com.example.teacher.model.Routine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoutineMapper {

    @Mapping(target = "user", source = "user")
    RoutineResponse toResponse(Routine routine);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "routineExercises", ignore = true)
    @Mapping(target = "activityLogs", ignore = true)
    Routine toEntity(RoutineRequest request);
}
