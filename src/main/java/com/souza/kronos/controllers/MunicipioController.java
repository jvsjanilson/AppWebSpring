package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Municipio;
import com.souza.kronos.services.EstadoService;
import com.souza.kronos.services.MunicipioService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/municipios")
public class MunicipioController {
    
    @Autowired
    MunicipioService service;

    @Autowired
    EstadoService serviceEstado;

    @GetMapping
    public String index(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        Model model)
    {
        Page<Municipio> list = service.listAll(page, size);
        model.addAttribute("list", list);
        return "/municipio/index";

    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Municipio obj = new Municipio();
        List<Estado> listEstados = serviceEstado.lookup();
        model.addAttribute("listEstados", listEstados);
        model.addAttribute("obj", obj);
        return "/municipio/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Municipio obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/municipio/create";
        } else {
            service.create(obj);
            return "redirect:/municipios";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Municipio obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/municipio/destroy";
    }

    @PostMapping("/destroy/municipio")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);
        return "redirect:/estados";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        List<Estado> listEstados = serviceEstado.lookup();
        Municipio obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        model.addAttribute("listEstados", listEstados);
        return "/municipio/update";
    }

    @PostMapping("/update")
    public String update( @Valid Municipio obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "municipio/update";
        }

        service.update(obj);

        return "redirect:/municipios";
    }
}
