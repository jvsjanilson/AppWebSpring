package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Pais;
import com.souza.kronos.services.EstadoService;
import com.souza.kronos.services.PaisService;

@Controller
@RequestMapping("/estados")
public class EstadoController {
    
    @Autowired
    EstadoService service;

    @Autowired
    PaisService servicePais;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model
    )
    {
        Page<Estado> list = service.listAll(page, size);
        model.addAttribute("list", list);
        model.addAttribute("currentPage", list.getNumber());
        model.addAttribute("totalPages", list.getTotalPages());
        return "/estado/index";

    }

    @PostMapping("/search")
    public String search(
        @RequestParam(defaultValue = "", required = false) String q,
        Model model )
    {
        if (q != "") {
            Page<Estado> list;
            list = service.search(q);
            model.addAttribute("q", q);
            model.addAttribute("list", list);
            model.addAttribute("currentPage", list.getNumber());
            model.addAttribute("totalPages", list.getTotalPages());
            return "/estado/index";
        } 
        
        return "redirect:/estados";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Estado obj = new Estado();
        List<Pais> listPais = servicePais.lookup();
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
        List<Pais> listPais = servicePais.lookup();
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
