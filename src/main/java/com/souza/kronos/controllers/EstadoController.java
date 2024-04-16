package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Pais;
import com.souza.kronos.repositories.PaisRepository;
import com.souza.kronos.services.EstadoService;
import com.souza.kronos.services.PaisService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/estados")
public class EstadoController {
    
    @Autowired
    EstadoService service;

    @Autowired
    PaisService servicePais;

    @GetMapping
    public String index(Model model)
    {
        List<Estado> list = service.listAll();
        model.addAttribute("list", list);
        return "/estado/index";

    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Estado obj = new Estado();
        List<Pais> listPais = servicePais.listAll();
        model.addAttribute("listPais", listPais);
        model.addAttribute("obj", obj);
        return "/estado/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Estado obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/estado/create";
        } else {
            service.create(obj);
            return "redirect:/estados";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Estado obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/estado/destroy";
    }

    @PostMapping("/destroy/estado")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);

        return "redirect:/estados";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        List<Pais> listPais = servicePais.listAll();
        Estado obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        model.addAttribute("listPais", listPais);
        return "/estado/update";
    }

    @PostMapping("/update")
    public String update( @Valid Estado obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "estado/update";
        }

        service.update(obj);

        return "redirect:/estados";
    }
}
