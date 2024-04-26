package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Categoria;
import com.souza.kronos.repositories.CategoriaRepository;

@Service
public class CategoriaService {
 
    @Autowired
    CategoriaRepository repository;

    public Page<Categoria> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

    public Page<Categoria> search(String search)
    {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        return repository.findByDescricaoContaining(search, pageRequest);
    }

    public List<Categoria> lookup()
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
