package com.miu.propertymanagement.service.impl;

import com.miu.propertymanagement.domain.Credential;
import com.miu.propertymanagement.domain.User;
import com.miu.propertymanagement.domain.dto.*;
import com.miu.propertymanagement.domain.enums.UserType;
import com.miu.propertymanagement.integration.Mapper.ListMapper;
import com.miu.propertymanagement.integration.exception.PMSException;
import com.miu.propertymanagement.integration.jwt.Jwt;
import com.miu.propertymanagement.repository.UserRepository;
import com.miu.propertymanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ListMapper listMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Jwt jwtUtil = new Jwt();

    @Override
    public ResponseEntity<?> login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findUserByCredential_Email(loginRequestDTO.getEmail()).orElseThrow(() -> new PMSException("User not found"));
        if (passwordEncoder.matches(loginRequestDTO.getPassword(), user.getCredential().getPassword())) {
            String token = jwtUtil.generateToken(loginRequestDTO.getEmail(), user.getUserType().toString());
            LoginResponseDTO result = new LoginResponseDTO();
            result.setToken(token);
            result.setUser(modelMapper.map(user, UserResponseDTO.class));
            return ResponseEntity.ok(result);
        } else {
            throw new PMSException("Invalid credentials");
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequestDTO registerRequestDTO) {
        Optional<User> existingUser = userRepository.findUserByCredential_Email(registerRequestDTO.getEmail());
        if (existingUser.isPresent())
            throw new PMSException("User with email: " + registerRequestDTO.getEmail() + " is already registered");

        User user = modelMapper.map(registerRequestDTO, User.class);

        String encodedPassword = passwordEncoder.encode(registerRequestDTO.getPassword());
        Credential credential = new Credential();
        user.setCredential(credential);
        user.getCredential().setEmail(registerRequestDTO.getEmail());
        user.getCredential().setPassword(encodedPassword);
        user.setActive(user.getUserType() != UserType.Owner);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(savedUser, RegisterResponseDTO.class));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return (List<UserResponseDTO>) listMapper.mapList(userRepository.findAll(), new UserResponseDTO());
    }

    @Override
    public UserDetailResponseDTO getUserById(Integer id) {
//        return modelMapper.map(userRepository.findUserByIdRewritten(id), UserDetailResponseDTO.class);
        return modelMapper.map(userRepository.findById(id).orElseGet(null), UserDetailResponseDTO.class);
    }

    @Override
    public UserResponseDTO changeStatusByUser(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new PMSException("User not found");
        User user = userOptional.get();

        user.setActive(!user.getActive());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> changeStatusRequests() {
        return (List<UserResponseDTO>) listMapper.mapList(userRepository.findAllByUserTypeAndActiveIsFalse(UserType.Owner), new UserResponseDTO());
    }
}
