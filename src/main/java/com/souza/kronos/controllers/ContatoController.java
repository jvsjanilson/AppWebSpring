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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.souza.kronos.models.Contato;
import com.souza.kronos.models.Estado;
import com.souza.kronos.models.Municipio;
import com.souza.kronos.services.ContatoService;
import com.souza.kronos.services.EstadoService;
import com.souza.kronos.services.MunicipioService;

@Controller
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    ContatoService service;

    @Autowired
    EstadoService estadoService;

    @Autowired
    MunicipioService municipioService;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,    
        Model model)
    {
        Page<Contato> list = service.listAll(page, size);
        model.addAttribute("list", list);
        return "/contato/index";
    }

     @PostMapping("/search")
    public String search(
        @RequestParam(defaultValue = "", required = false) String q,
        Model model )
    {
        if (q != "") {
            Page<Contato> list;
            list = service.search(q);
            model.addAttribute("q", q);
            model.addAttribute("list", list);
            model.addAttribute("currentPage", list.getNumber());
            model.addAttribute("totalPages", list.getTotalPages());
            return "/contato/index";
        } 
        
        return "redirect:/contatos";
    }    


    @GetMapping("/create")
    public String create(Model model)
    {
        Contato obj = new Contato();
        obj.setEstado(estadoService.findById(1L));
        List<Estado> listEstados = estadoService.lookup();
        List<Municipio> listMunicipios = municipioService.findByEstado(obj.getEstado());

        model.addAttribute("obj", obj);
        model.addAttribute("listEstados", listEstados);
        model.addAttribute("listMunicipios", listMunicipios);
        return "/contato/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Contato obj, BindingResult bindingResult, Model model)
    {
        
        service.validarContato(obj, bindingResult);
        
        if (bindingResult.hasErrors()) {
            List<Estado> listEstados = estadoService.lookup();
            List<Municipio> listMunicipios = municipioService.findByEstado(obj.getEstado());
           
            model.addAttribute("listEstados", listEstados);
            model.addAttribute("listMunicipios", listMunicipios);
            return "/contato/create";
        } else {
            service.create(obj);
            return "redirect:/contatos";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Contato obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/contato/destroy";
    }

    @PostMapping("/destroy/contato")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);

        return "redirect:/contatos";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Contato obj = service.findById(id) ;
             
        List<Estado> listEstados = estadoService.lookup();
        List<Municipio> listMunicipios = municipioService.findByEstado(obj.getEstado());

        model.addAttribute("obj", obj);
        model.addAttribute("listMunicipios", listMunicipios);
        model.addAttribute("listEstados", listEstados);
        return "/contato/update";
    }

    @PostMapping("/update")
    public String update( @Valid @ModelAttribute("obj") Contato obj, BindingResult bindingResult)
    {
        service.validarContato(obj, bindingResult);
        

        if (bindingResult.hasErrors()) {


            return "contato/update";
        }
        
        
        service.update(obj);

        return "redirect:/contatos";
    }


}
