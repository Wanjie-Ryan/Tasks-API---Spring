package com.tasks.api.projects.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tasks")
public class Task {

    @Id
    private UUID id;

    @NotBlank(message = "Task Name is Required")
    private String name;

    @NotBlank(message = "Task Description is Required")
    private String description;

    @NotNull(message = "Task completion status is Required")
    private Boolean completed = false;

    @NotNull(message = "Task creation time is required")
    private LocalDateTime createdAt;

    @NotNull(message = "Task deadline time is required")
    private LocalDateTime deadline;

    @ManyToOne
    @NotNull(message = "Admin is Required")
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }


}
