package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Estado;
import com.souza.kronos.repositories.EstadoRepository;

@Service
public class EstadoService {
    
    @Autowired
    EstadoRepository repository;

    public Page<Estado> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

    public List<Estado> lookup()
    {
        return repository.findAll(Sort.by("uf"));
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
