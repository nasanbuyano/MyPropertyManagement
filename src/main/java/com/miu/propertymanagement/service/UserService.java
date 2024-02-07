package com.miu.propertymanagement.service;

import com.miu.propertymanagement.domain.dto.LoginRequestDTO;
import com.miu.propertymanagement.domain.dto.RegisterRequestDTO;
import com.miu.propertymanagement.domain.dto.UserDetailResponseDTO;
import com.miu.propertymanagement.domain.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> login(LoginRequestDTO loginRequestDTO);

    ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO);

    List<UserResponseDTO> getAllUsers();

    UserDetailResponseDTO getUserById(Integer id);

    UserResponseDTO changeStatusByUser(Integer id);

    List<UserResponseDTO> changeStatusRequests();
}
