package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {
    
}
