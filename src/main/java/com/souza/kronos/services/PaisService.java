package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Pais;
import com.souza.kronos.repositories.PaisRepository;

@Service
public class PaisService {
    
   
    @Autowired
    PaisRepository repository;

    public Page<Pais> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

    public Page<Pais> search(String search)
    {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        return repository.findByDescricaoContaining(search, pageRequest);
    }

    public List<Pais> lookup()
    {
        return repository.findAll(Sort.by("descricao"));
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
