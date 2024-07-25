package com.tasks.api.projects.service.project;

import com.tasks.api.projects.models.Auth;
import com.tasks.api.projects.models.Project;
import com.tasks.api.projects.repository.ProjectRepository;
import com.tasks.api.projects.service.DTO.DeleteProjectResponseDTO;
import com.tasks.api.projects.service.DTO.ProjectResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectAdminService {

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
        responseDTO.setMessage("Project was created successfully");
        responseDTO.setName(savedProject.getName());
        responseDTO.setDescription(savedProject.getDescription());
        responseDTO.setCompleted(savedProject.getCompleted());
        responseDTO.setAdmin(savedProject.getAdmin().getName());
        responseDTO.setCreatedAt(savedProject.getCreatedAt());
        responseDTO.setDeadline(savedProject.getDeadline());

        return responseDTO;


    }

    // method to get all projects
    public List<Project> getAllProjects(){

        return projectRepo.findAll();

    }

    // method to get a project by its id
    public Project getProjectById(UUID id) throws EntityNotFoundException {

        Optional<Project> getProject = projectRepo.findById(id);
        if(getProject.isEmpty()){
            throw new EntityNotFoundException("Project of id " + id + " not found");

        }

        return getProject.get();

    }

    // method to delete a project
    public DeleteProjectResponseDTO deleteProject (UUID id) throws EntityNotFoundException{

        Project project = getProjectById(id);

        projectRepo.delete(project);

        DeleteProjectResponseDTO responseDTO = new DeleteProjectResponseDTO();
        responseDTO.setMessage("Project Deleted Successfully");
        responseDTO.setProjectName(project.getName());
        responseDTO.setProjectDescription(project.getDescription());
        responseDTO.setCreatedBy(project.getAdmin().getName());

        return responseDTO;

    }

    // method to update a project

    public ProjectResponseDTO updateProject(UUID id, Project project)throws EntityNotFoundException{

        Project updateProject = getProjectById(id);


        updateProject.setName(project.getName());
        updateProject.setDescription(project.getDescription());
        updateProject.setCompleted(!updateProject.getCompleted());
        updateProject.setDeadline(project.getDeadline());

        Project saveUpdatedProject = projectRepo.save(updateProject);
        ProjectResponseDTO response = new ProjectResponseDTO();
        response.setMessage("Project updated successfully");
        response.setName(saveUpdatedProject.getName());
        response.setDescription(saveUpdatedProject.getDescription());
        response.setCompleted(saveUpdatedProject.getCompleted());
        response.setDeadline(saveUpdatedProject.getDeadline());


        return response;
    }


    // method to search for a  project by its name

    public List<Project> searchProjectByName(String projectname){
        List<Project> getProjects = projectRepo.searchProjectName(projectname);
        return getProjects;
    }

    // method to search for a project by the name of the admin
    public List<Project> searchProjectByadminName(String adminname){
        List<Project> getProjects = projectRepo.searchProjectByAdminname(adminname);
        return getProjects;
    }

    // method to filter projects based on their status
    public List<Project> filterProjectsByStatus(boolean completed){
        List<Project> getProjects = projectRepo.filterProjectsByStatus(completed);
        return getProjects;
    }




}
