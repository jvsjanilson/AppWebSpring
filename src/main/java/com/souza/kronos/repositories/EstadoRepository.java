package com.souza.kronos.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
 
    Page<Estado> findByUfContainingOrDescricaoContaining(String search1, String search2, PageRequest page);
}
