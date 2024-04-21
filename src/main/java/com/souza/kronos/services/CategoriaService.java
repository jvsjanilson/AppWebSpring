package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.Categoria;
import com.souza.kronos.repositories.CategoriaRepository;

@Service
public class CategoriaService {
 
    @Autowired
    CategoriaRepository repository;

    public List<Categoria> listAll()
    {
        return repository.findAll();
    }

    public void create(Categoria model)
    {
        repository.save(model);
    }

    public void update(Categoria model)
    {
        repository.save(model);
    }

    public Categoria findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }
}
