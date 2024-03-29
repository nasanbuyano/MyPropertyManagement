package com.miu.propertymanagement.controller;

import com.miu.propertymanagement.domain.dto.LoginRequestDTO;
import com.miu.propertymanagement.domain.dto.RegisterRequestDTO;
import com.miu.propertymanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO LoginRequestDTO) {
        return userService.login(LoginRequestDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.register(registerRequestDTO);
    }

}
