package com.souza.kronos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.User;
import com.souza.kronos.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> listAll()
    {
        return repository.findAll();
    }

    public void create(User user)
    {
        repository.save(user);
    }

}
