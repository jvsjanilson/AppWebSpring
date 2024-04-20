package com.souza.kronos.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Pais;
import com.souza.kronos.repositories.PaisRepository;

@Service
public class PaisService {
    
   
    @Autowired
    PaisRepository repository;

    public List<Pais> listAll()
    {
        return repository.findAll();
    }

    public void create(Pais model)
    {
        repository.save(model);
    }

    public void update(Pais model)
    {
        repository.save(model);
    }

    public Pais findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }    
}
