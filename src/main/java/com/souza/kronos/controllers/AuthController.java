package com.souza.kronos.controllers;

import com.souza.kronos.models.User;
import com.souza.kronos.services.CargoService;
import com.souza.kronos.services.UserService;
import com.souza.kronos.utils.SenhaUtils;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class AuthController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login() 
    {
        return "/user/login";
    }   

    @GetMapping("/register")
    public String showFormRegister(Model model)
    {
        return "/user/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("obj") User obj, BindingResult bindingResult)
    {
        var cargo = cargoService.findById(2L);
        obj.setPassword(SenhaUtils.encode(obj.getPassword()));

        obj.setCargo(cargo);
        service.create(obj);
        return "redirect:/login";
    }
   
}
