package com.souza.kronos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.souza.kronos.models.Cargo;
import com.souza.kronos.services.CargoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    CargoService service;

    @GetMapping
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model)
    {
        Page<Cargo> list = service.listAll(page, size);
        model.addAttribute("list", list);
        return "/cargo/index";
    }
    
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "", required = false) String q, Model model )
    {
        if (q != "") {
            Page<Cargo> list;
            list = service.search(q);
            model.addAttribute("q", q);
            model.addAttribute("list", list);
            model.addAttribute("currentPage", list.getNumber());
            model.addAttribute("totalPages", list.getTotalPages());
            return "/cargo/index";
        } 
        
        return "redirect:/cargos";
    }    

    @GetMapping("/create")
    public String create(Model model)
    {
        Cargo obj = new Cargo();
        model.addAttribute("obj", obj);
        return "/cargo/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Cargo obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/cargo/form";
        } else {
            service.create(obj);
            return "redirect:/cargos";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Cargo obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/cargo/form";
    }

    @PostMapping("/edit/{id}")
    public String update( @Valid Cargo obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "cargo/form";
        }

        service.update(obj);
        return "redirect:/cargos";
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Cargo obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/cargo/destroy";
    }

    @PostMapping("/destroy/{id}")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);
        return "redirect:/cargos";
    }
}
