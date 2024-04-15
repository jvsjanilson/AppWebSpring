package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    
}
