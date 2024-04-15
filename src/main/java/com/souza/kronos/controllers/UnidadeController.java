package com.souza.kronos.controllers;

//framework
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//App
import com.souza.kronos.models.Unidade;
import com.souza.kronos.services.UnidadeService;


@Controller
@RequestMapping("/unidades")
public class UnidadeController {
    
    @Autowired
    UnidadeService service;

    @GetMapping
    public String index(Model model)
    {
        List<Unidade> listUsers = service.listAll();
        model.addAttribute("listUnidades", listUsers);
        return "/unidade/index";

    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Unidade unidade = new Unidade();
        model.addAttribute("unidade", unidade);
        return "/unidade/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("unidade") Unidade unidade, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/unidade/create";
        } else {
            service.create(unidade);
            return "redirect:/unidades";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Unidade unidade = service.findById(id) ;
        model.addAttribute("unidade", unidade);
        return "/unidade/destroy";
    }

    @PostMapping("/destroy/unidade")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);

        return "redirect:/unidades";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Unidade unidade = service.findById(id) ;
        model.addAttribute("unidade", unidade);
        return "/unidade/update";
    }

    @PostMapping("/update")
    public String update( @Valid Unidade unidade, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "unidade/update";
        }

        service.update(unidade);

        return "redirect:/unidades";
    }
    

}
