package com.tasks.api.projects.controller;

import com.tasks.api.projects.configs.JwtService;
import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.models.Project;
import com.tasks.api.projects.service.DTO.ProjectResponseDTO;
import com.tasks.api.projects.service.project.ProjectAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PostMapping("/create")
//    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody Project project, @RequestHeader("Authorization") String token){
//        return null;
//    }

    @GetMapping
    public ResponseEntity<String> hello(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);

        // Extract user email (or username) from the token
        String email = jwtService.extractUserId(jwt);

        // Load the user details using the extracted email
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Validate the token
        if (!jwtService.isTokenvalid(jwt, userDetails)) {
            return ResponseEntity.status(403).body("Invalid token");
        }

        // Extract roles from the token
        List<String> roles = (List<String>) jwtService.extractAllClaims(jwt).get("roles");

        // Assuming the Auth model represents the authenticated user
        Auth auth = new Auth();
        auth.setEmail(email);

        // Print roles for demonstration
        String rolesString = roles.stream().collect(Collectors.joining(", "));

        return ResponseEntity.ok("Hello " + email + " with roles: " + rolesString);
    }



}
