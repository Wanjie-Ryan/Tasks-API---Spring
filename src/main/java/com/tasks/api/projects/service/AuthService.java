package com.tasks.api.projects.service;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.repository.AuthRepository;
import com.tasks.api.projects.service.DTO.LoginResponseDTO;
import com.tasks.api.projects.service.DTO.RegisterResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthRepository authRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public RegisterResponseDTO register(Auth auth)throws  Exception{

        if(authRepo.findByEmail(auth.getEmail()).isPresent()){
            throw new Exception("Email already exists");
        }

        Auth registerAuth = new Auth();
        registerAuth.setName(auth.getName());
        if(isValidEmail(auth.getEmail())){
        registerAuth.setEmail(auth.getEmail());
        }
        else{
            log.info("Invalid email");
            throw new Exception("Invalid email");

        }
        registerAuth.setPassword(passwordEncoder.encode(auth.getPassword()));
        registerAuth.setRole(auth.getRole());

        Auth savedAuth = authRepo.save(registerAuth);
        RegisterResponseDTO responseDto = new RegisterResponseDTO();
        responseDto.setMessage("Registration Successful");
        responseDto.setName(savedAuth.getName());
        responseDto.setEmail(savedAuth.getEmail());
        responseDto.setRole(savedAuth.getRole().toString());

        return responseDto;


    }

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }


    public LoginResponseDTO login (String email, String password){

        Authentication auths = authManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));

        Auth auth = (Auth) auths.getPrincipal();
        String jwtToken = jwtService.generateToken(auth);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setEmail(auth.getEmail());
        response.setToken(jwtToken);
        response.setMessage("Login Successful");
        response.setRole(auth.getRole().toString());
        return response;


    }


}
