package com.tasks.api.projects.repository;

import com.tasks.api.projects.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<Auth, UUID>{

    Optional<Auth> findByEmail(String email);
}
