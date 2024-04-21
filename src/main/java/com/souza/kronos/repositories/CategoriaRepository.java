package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
