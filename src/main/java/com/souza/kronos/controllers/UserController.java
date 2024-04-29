package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;
import com.souza.kronos.models.Cargo;
import com.souza.kronos.models.User;
import com.souza.kronos.services.CargoService;
import com.souza.kronos.services.UserService;
import com.souza.kronos.utils.SenhaUtils;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    CargoService cargoService;

    @GetMapping()
    public String index(Model model)
    {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "/user/index.html";
    }
    
    @GetMapping("/create")
    public String create(Model model)
    {
        User user = new User();
        List<Cargo> listCargos = cargoService.listAll();

        model.addAttribute("user", user);
        model.addAttribute("listCargos", listCargos);
        return "/user/create.html";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {

        user.setPassword(SenhaUtils.encode(user.getPassword()));

        if (bindingResult.hasErrors()) {
            return "/user/create";
        } else {
            service.create(user);
            return "redirect:/users";
        }
    }

}
