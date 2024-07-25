package com.tasks.api.projects.service.project;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.models.Project;
import com.tasks.api.projects.repository.ProjectRepository;
import com.tasks.api.projects.service.DTO.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    // method to create a project
    public ProjectResponseDTO createProject(Project project, Auth auth) throws Exception{

    Project createdProject = new Project();
        createdProject.setName(project.getName());
        createdProject.setDescription(project.getDescription());
        createdProject.setCompleted(project.getCompleted());
        createdProject.setAdmin(auth);
        createdProject.setCreatedAt(LocalDateTime.now());
        createdProject.setDeadline(project.getDeadline());

        Project savedProject = projectRepo.save(createdProject);

        ProjectResponseDTO responseDTO = new ProjectResponseDTO();
        responseDTO.setName(savedProject.getName());
        responseDTO.setDescription(savedProject.getDescription());
        responseDTO.setCompleted(savedProject.getCompleted());
        responseDTO.setAdmin(savedProject.getAdmin().getName());
        responseDTO.setCreatedAt(savedProject.getCreatedAt());
        responseDTO.setDeadline(savedProject.getDeadline());

        return responseDTO;


    }

    // method to get all projects





}
