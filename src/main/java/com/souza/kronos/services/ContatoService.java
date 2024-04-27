package com.souza.kronos.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.Contato;
import com.souza.kronos.repositories.ContatoRepository;
import com.souza.kronos.repositories.UtilService;
import org.springframework.validation.BindingResult;

@Service
public class ContatoService {

    @Autowired
    ContatoRepository repository;

    @Autowired
    UtilService utilService;

    public void validarContato(Contato contato, BindingResult bindingResult) {
        String cpf = contato.getDocumento();
        
        if (!utilService.validarDocumento(cpf)) {
            bindingResult.rejectValue("documento", "cpfcnpj.invalido", "CPF/CNPJ inválido");
        }
    }

    public Page<Contato> listAll(int pageNumber, int pageSize)
    {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return repository.findAll(pageRequest);
    }

     public Page<Contato> search(String search)
    {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").descending());
        return repository.search(search, pageRequest);
    }

    public List<Contato> listAll()
    {
        return repository.findAll();
    }

    public void create(Contato model)
    {
        repository.save(model);
    }

    public void update(Contato model) 
    {
        repository.save(model);
    }

    public Contato findById(Long id)
    {
        return repository.findById(id).get();
        
    }

    public void destroy(Long id)
    {
        repository.deleteById(id);
    }       
}
