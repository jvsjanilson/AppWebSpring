package com.souza.kronos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    Page<Unidade> findByDescricaoContainingOrCodigoContaining(String search1, String search2, PageRequest page);   
}
