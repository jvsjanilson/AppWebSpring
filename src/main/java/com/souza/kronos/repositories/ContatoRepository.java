package com.souza.kronos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Page<Contato> findByNomeContainingOrNomeFantasiaContainingOrDocumentoContaining(String search1, String search2, String search3, PageRequest page);   
}
