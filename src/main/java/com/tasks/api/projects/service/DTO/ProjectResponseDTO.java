package com.tasks.api.projects.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NegativeOrZero;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDTO {

    private String message;
    private String name;
    private String description;
    private Boolean completed;
    private String admin;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;


}
