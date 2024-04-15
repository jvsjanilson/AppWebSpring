package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
}
