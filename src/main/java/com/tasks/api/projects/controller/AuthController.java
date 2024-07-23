package com.tasks.api.projects.controller;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.repository.AuthRepository;
import com.tasks.api.projects.service.AuthService;
import com.tasks.api.projects.service.DTO.RegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRepository authRepo;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register (@RequestBody Auth auth) throws Exception{



    }




}
