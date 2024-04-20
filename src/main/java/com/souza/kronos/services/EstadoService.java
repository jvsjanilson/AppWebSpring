package com.souza.kronos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.Estado;
import com.souza.kronos.repositories.EstadoRepository;

@Service
public class EstadoService {
    
    @Autowired
    EstadoRepository repository;

   

    public List<Estado> listAll()
    {
        return repository.findAll();
    }

    public void create(Estado model)
    {
        repository.save(model);
    }

    public void update(Estado model)
    {
        repository.save(model);
    }

    public Estado findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }       
}
