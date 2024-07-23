package com.tasks.api.projects.service;

import com.tasks.api.projects.configs.JwtService;
import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.repository.AuthRepository;
import com.tasks.api.projects.service.DTO.RegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private AuthenticationManager authManager;

    public RegisterResponseDTO register(Auth auth){

        Auth registerAuth = new Auth();
        registerAuth.setName(auth.getName());
        registerAuth.setEmail(auth.getEmail());
        registerAuth.setPassword(passwordEncoder.encode(auth.getPassword()));
        registerAuth.setRole(auth.getRole());

        Auth savedAuth = authRepo.save(registerAuth);
        RegisterResponseDTO responseDto = new RegisterResponseDTO();
        responseDto.setName(savedAuth.getName());
        responseDto.setEmail(savedAuth.getEmail());
        responseDto.setRole(savedAuth.getRole().toString());

        return responseDto;


    }


}
