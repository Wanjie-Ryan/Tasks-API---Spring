package com.tasks.api.projects.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteProjectResponseDTO {

    private String message;
    private String projectName;
    private String projectDescription;
    private String createdBy;
}
