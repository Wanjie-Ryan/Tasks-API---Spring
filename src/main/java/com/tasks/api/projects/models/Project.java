package com.tasks.api.projects.models;

import com.tasks.api.projects.models.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="projects")
public class Project {

    @Id
    private UUID id;
    private String name;
    private String description;
    private Status status;


}
