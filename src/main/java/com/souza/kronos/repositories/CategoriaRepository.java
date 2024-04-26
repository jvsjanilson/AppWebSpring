package com.souza.kronos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Page<Categoria> findByDescricaoContaining(String search, PageRequest page);
}
