package com.souza.kronos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.souza.kronos.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    @Query("select u from Contato u where u.nome like %:value% or u.nomeFantasia like %:value% or documento like %:value% ")
    Page<Contato> search(String value, PageRequest page);
}
