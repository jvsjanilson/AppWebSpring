package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.Produto;
import com.souza.kronos.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public List<Produto> listAll()
    {
        return repository.findAll();
    }

    public void create(Produto model)
    {
        repository.save(model);
    }

    public void update(Produto model)
    {
        repository.save(model);
    }

    public Produto findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }
}
