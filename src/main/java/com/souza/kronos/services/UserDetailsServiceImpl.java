package com.souza.kronos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.souza.kronos.models.User;
import com.souza.kronos.models.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.buscarPorEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário nao encontrado"));
            
        return new UserDetailsImpl(user);
    }

}
