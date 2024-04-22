package com.souza.kronos.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Municipio;
import com.souza.kronos.repositories.MunicipioRepository;

@Service
public class MunicipioService {
     
    @Autowired
    MunicipioRepository repository;

    public Page<Municipio> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageRequest);
    }

    public void create(Municipio model)
    {
        repository.save(model);
    }

    public void update(Municipio model)
    {
        repository.save(model);
    }

    public Municipio findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
        repository.deleteById(id);
    }       

    public List<Municipio> findByEstado(Estado estado) 
    {
        return repository.findByEstado(estado);
    }

    public List<Municipio> findByEstadoId(Long id)
    {
        return repository.findByEstadoId(id);
    }

}
