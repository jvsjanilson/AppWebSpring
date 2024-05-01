package com.souza.kronos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.souza.kronos.models.User;
import com.souza.kronos.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    public List<User> listAll()
    {
        return repository.findAll();
    }

    public void create(User user)
    {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public Optional<User> buscarPorEmail(String email)
    {
        return repository.findByEmail(email);
    }

}
