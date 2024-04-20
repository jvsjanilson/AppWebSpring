package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.souza.kronos.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
