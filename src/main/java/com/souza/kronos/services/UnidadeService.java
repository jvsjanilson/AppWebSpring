package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Unidade;
import com.souza.kronos.repositories.UnidadeRepository;

@Service
public class UnidadeService {
    
    @Autowired
    UnidadeRepository repository;

    public List<Unidade> listAll()
    {
        return repository.findAll();
    }

    public void create(Unidade unidade)
    {
        repository.save(unidade);
    }

    public void update(Unidade unidade)
    {
        repository.save(unidade);
    }

    public Unidade findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }

}
