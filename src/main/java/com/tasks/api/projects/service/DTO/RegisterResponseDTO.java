package com.tasks.api.projects.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {

    private String message;
    private String name;
    private String email;
    private String role;

}
