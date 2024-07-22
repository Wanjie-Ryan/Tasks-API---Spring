package com.tasks.api.projects.models;

import com.tasks.api.projects.models.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    // notBlank is used for String fields while notNull can be used for any type of fields
    @NotBlank(message = "Project Name is Required")
    private String name;
    @NotBlank(message = "Project Description is Required")
    private String description;
    @NotNull(message = "Project Status is Required")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.Pending;
    @NotNull(message = "Admin is Required")
    @ManyToOne
    @JoinColumn(name="admin_id", nullable = false)
    private Auth admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Task> tasks = new ArrayList<>();
    @NotNull(message = "Project creation time is required")
    private LocalDateTime createdAt;
    @NotNull(message = "Project Deadline is required")
    private LocalDateTime deadline;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

}
