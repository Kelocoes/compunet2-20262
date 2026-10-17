package com.example.teacher.mappers;

import com.example.teacher.controller.rest.dto.UserResponse;
import com.example.teacher.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "userName", source = "name")
    UserResponse toResponse(User user);
}
