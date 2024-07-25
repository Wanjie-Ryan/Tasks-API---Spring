package com.tasks.api.projects.controller;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.repository.AuthRepository;
import com.tasks.api.projects.service.AuthService;
import com.tasks.api.projects.service.DTO.LoginResponseDTO;
import com.tasks.api.projects.service.DTO.RegisterResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthRepository authRepo;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register (@RequestBody Auth auth) throws Exception{

//        try{
            RegisterResponseDTO response = authService.register(auth);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        }
//        catch(Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody Auth auth ) throws Exception{
//        try{
            LoginResponseDTO response = authService.login(auth.getEmail(), auth.getPassword());

            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }
//        catch(Exception e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }



}
