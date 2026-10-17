package com.example.teacher.service;

import com.example.teacher.controller.rest.dto.LoginRequestDTO;
import com.example.teacher.controller.rest.dto.TokenResponseDTO;

public interface IAuthService {

    TokenResponseDTO login(LoginRequestDTO request);
}
