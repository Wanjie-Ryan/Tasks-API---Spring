package com.tasks.api.projects.configs;

import com.tasks.api.projects.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.IllegalFormatException;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Autowired
    private AuthRepository authRepo;

    @Bean
    // ensures that the input is a valid UUID
    public UserDetailsService userDetailsService(){
//        return userId ->{
//            try{
//                UUID uuid = UUID.fromString(userId);
//                return authRepo.findById(uuid).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//            }
//            catch(IllegalArgumentException e){
//                throw new UsernameNotFoundException("Invalid User Id");
//            }
//        };
        return email -> authRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Bean
    public AuthenticationProvider authProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;

    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
