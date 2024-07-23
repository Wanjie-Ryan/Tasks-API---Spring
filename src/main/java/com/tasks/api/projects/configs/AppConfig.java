package com.tasks.api.projects.configs;

import com.tasks.api.projects.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Autowired
    private AuthRepository authRepo;

}
