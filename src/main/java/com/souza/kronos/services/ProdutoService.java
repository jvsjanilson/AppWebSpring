package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.Produto;
import com.souza.kronos.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Page<Produto> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }


    public List<Produto> lookup()
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
