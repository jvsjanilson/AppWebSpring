package com.souza.kronos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
}
