package com.tasks.api.projects.models;

import com.tasks.api.projects.models.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="projects")
public class Project {

    @Id
    private UUID id;
    @NotBlank(message = "Project Name is Required")
    private String name;
    @NotBlank(message = "Project Description is Required")
    private String description;
    @NotBlank(message = "Project Status is Required")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.Pending;
    @NotBlank(message = "Admin is Required")
    @ManyToOne
    private Auth admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();


}
