package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.souza.kronos.models.Categoria;
import com.souza.kronos.services.CategoriaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @GetMapping
    public String index(Model model)
    {
        List<Categoria> list = service.listAll();
        model.addAttribute("list", list);
        return "/categoria/index";

    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Categoria obj = new Categoria();
        model.addAttribute("obj", obj);
        return "/categoria/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Categoria obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/categoria/create";
        } else {
            service.create(obj);
            return "redirect:/categorias";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Categoria obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/categoria/destroy";
    }

    @PostMapping("/destroy/categoria")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);
        return "redirect:/categorias";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Categoria obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/categoria/update";
    }

    @PostMapping("/update")
    public String update( @Valid Categoria obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "categoria/update";
        }

        service.update(obj);
        return "redirect:/categorias";
    }
}
