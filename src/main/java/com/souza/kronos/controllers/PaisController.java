package com.souza.kronos.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import com.souza.kronos.models.Pais;
import com.souza.kronos.services.PaisService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/pais")
public class PaisController {
    @Autowired
    PaisService service;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model)
    {
        Page<Pais> list = service.listAll(page, size);
        model.addAttribute("list", list);
        return "/pais/index";

    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Pais obj = new Pais();
        model.addAttribute("obj", obj);
        return "/pais/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Pais obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/pais/create";
        } else {
            service.create(obj);
            return "redirect:/pais";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Pais obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/pais/destroy";
    }

    @PostMapping("/destroy/pais")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);

        return "redirect:/pais";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Pais obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/pais/update";
    }

    @PostMapping("/update")
    public String update( @Valid Pais obj, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "pais/update";
        }

        service.update(obj);

        return "redirect:/pais";
    }
}
