package com.tasks.api.projects.controller;

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

import java.util.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @Autowired
    private ProjectAdminService adminService;

//    @Autowired
//    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody Project project, @RequestHeader("Authorization") String token)throws Exception{


//        logger.info("Authorization header: " + token);
//        System.out.println("Received token: " + token);
//        // Ensure token is in the correct format
//        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
//
//        // Extract email from the JWT token
//        String email = jwtService.extractUserId(jwt);
//        logger.info("Extracted email: " + email);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        if (!jwtService.isTokenvalid(jwt, userDetails)) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        List<String> roles = (List<String>) jwtService.extractAllClaims(jwt).get("roles");
        Auth auth = new Auth();
//        auth.setEmail(email);
//
//        String rolesString = roles.stream().collect(Collectors.joining(", "));
        ProjectResponseDTO createdProject = adminService.createProject(project, auth);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<String> hello(@RequestHeader("Authorization") String token) {
//        String jwt = token.substring(7);
//
//        // Extract user email (or username) from the token
//        String email = jwtService.extractUserId(jwt);
//
//        // Load the user details using the extracted email
//        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//        // Validate the token
//        if (!jwtService.isTokenvalid(jwt, userDetails)) {
//            return ResponseEntity.status(403).body("Invalid token");
//        }
//
//        // Extract roles from the token
////        List<String> roles = (List<String>) jwtService.extractAllClaims(jwt).get("roles");
//
//        // Assuming the Auth model represents the authenticated user
//        Auth auth = new Auth();
//        auth.setEmail(email);
//
//        // Print roles for demonstration
////        String rolesString = roles.stream().collect(Collectors.joining(", "));
//
//        return ResponseEntity.ok("Hello " + email + " with roles: " );
//    }




}
