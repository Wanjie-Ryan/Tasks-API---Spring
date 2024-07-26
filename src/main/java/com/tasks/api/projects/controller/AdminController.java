package com.tasks.api.projects.controller;

import com.tasks.api.projects.configs.JwtService;
import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.models.Project;
import com.tasks.api.projects.service.DTO.ProjectResponseDTO;
import com.tasks.api.projects.service.project.ProjectAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ProjectAdminService adminService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody Project project, @RequestHeader("Authorization") String token)throws Exception{

        String jwt = token.substring(7);
        String email = jwtService.extractUserId(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!jwtService.isTokenvalid(jwt, userDetails)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Auth auth = new Auth();
        auth.setEmail(email);

        ProjectResponseDTO createdProject = adminService.createProject(project, auth);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }



}
