package com.tasks.api.projects.repository;

import com.tasks.api.projects.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    // custom method for searching the project by its name
    @Query("SELECT p FROM Project p WHERE p.name LIKE %:projectname%")
    List<Project> searchProjectName(@Param("projectname") String projectname);

    // custom method to search for a project by the admin who created it
    @Query("SELECT p FROM Project p WHERE p.admin.name LIKE %:adminname%")
    List<Project> searchProjectByAdminname(@Param("adminname") String adminname);

    // custom method to filter projects by those completed and those that are not
    @Query("SELECT p FROM Project p WHERE p.completed = ?1")
    List<Project> filterProjectsByStatus(boolean completed);
}
