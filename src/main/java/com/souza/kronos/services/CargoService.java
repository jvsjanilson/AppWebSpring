package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Cargo;
import com.souza.kronos.repositories.CargoRepository;

@Service
public class CargoService {
 
    @Autowired
    CargoRepository repository;

    public Page<Cargo> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

    public List<Cargo> listAll()
    {
        return repository.findAll();
    }

    public Page<Cargo> search(String search)
    {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        return repository.findByDescricaoContaining(search, pageRequest);
    }

    public List<Cargo> lookup()
    {
        return repository.findAll();
    }


    public void create(Cargo model)
    {
        repository.save(model);
    }

    public void update(Cargo model)
    {
        repository.save(model);
    }

    public Cargo findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
     repository.deleteById(id);
    }
}
