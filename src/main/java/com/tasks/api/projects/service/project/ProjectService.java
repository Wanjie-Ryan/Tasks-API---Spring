package com.tasks.api.projects.service.project;

import com.tasks.api.projects.models.Project;
import com.tasks.api.projects.repository.ProjectRepository;
import com.tasks.api.projects.service.DTO.ProjectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepo;

    public ProjectResponseDTO createProject(Project project) throws Exception{




    }





}
