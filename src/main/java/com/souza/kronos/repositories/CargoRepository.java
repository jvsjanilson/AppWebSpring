package com.souza.kronos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    Page<Cargo> findByDescricaoContaining(String search, PageRequest page);
}
