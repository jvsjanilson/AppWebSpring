package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);
}
