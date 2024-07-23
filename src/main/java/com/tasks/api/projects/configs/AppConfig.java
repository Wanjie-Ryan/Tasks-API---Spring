package com.tasks.api.projects.configs;

import com.tasks.api.projects.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Autowired
    private AuthRepository authRepo;

    @Bean
    public UserDetailsService userDetailsService(){
        return userId -> authRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }



    @Bean
    public AuthenticationProvider authProvider(){

    }



}
