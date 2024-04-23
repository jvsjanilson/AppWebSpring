package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Unidade;
import com.souza.kronos.repositories.UnidadeRepository;

@Service
public class UnidadeService {
    
    @Autowired
    UnidadeRepository repository;


    public Page<Unidade> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

    public List<Unidade> lookup()
    {
        return repository.findAll();
    }

    public void create(Unidade model)
    {
        repository.save(model);
    }

    public void update(Unidade model)
    {
        repository.save(model);
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
