package com.souza.kronos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByEstado(Estado estado);

    List<Municipio> findByEstadoId(Long id);

}
