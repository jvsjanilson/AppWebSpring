package com.souza.kronos.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import com.souza.kronos.models.Categoria;
import com.souza.kronos.models.Produto;
import com.souza.kronos.models.Unidade;
import com.souza.kronos.services.CategoriaService;
import com.souza.kronos.services.ProdutoService;
import com.souza.kronos.services.UnidadeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private static String UPLOAD_DIR = "uploads/";

    
    @Autowired
    ProdutoService service;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public String index(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,    
    Model model)
    {
        Page<Produto> list = service.listAll(page, size);
        model.addAttribute("list", list);
        return "/produto/index";
    }
    
    @PostMapping("/search")
    public String search(
        @RequestParam(defaultValue = "", required = false) String q,
        Model model )
    {
        if (q != "") {
            Page<Produto> list;
            list = service.search(q);
            model.addAttribute("q", q);
            model.addAttribute("list", list);
            model.addAttribute("currentPage", list.getNumber());
            model.addAttribute("totalPages", list.getTotalPages());
            return "/produto/index";
        } 
        
        return "redirect:/produtos";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Produto obj = new Produto();
        List<Unidade> listUnidades = unidadeService.lookup();
        List<Categoria> listCategorias = categoriaService.lookup();
        model.addAttribute("listUnidades", listUnidades);
        model.addAttribute("listCategorias", listCategorias);
        model.addAttribute("obj", obj);
        return "/produto/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("obj") Produto obj, BindingResult bindingResult)
    {
        System.out.println(obj.getPreco());
        
        if (bindingResult.hasErrors()) {
            return "/produto/create";
        } else {
            service.create(obj);
            return "redirect:/produtos";
        }
    }

    @GetMapping("/destroy/{id}")
    public String showFormDestroy(@PathVariable Long id, Model model)
    {
        Produto obj = service.findById(id) ;
        model.addAttribute("obj", obj);
        return "/produto/destroy";
    }

    @PostMapping("/destroy/produto")
    public String destroy(@RequestParam("id") Long id)
    {
        service.destroy(id);
        return "redirect:/produtos";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model)
    {
        Produto obj = service.findById(id) ;
        List<Unidade> listUnidades = unidadeService.lookup();
        List<Categoria> listCategorias = categoriaService.lookup();
        model.addAttribute("listUnidades", listUnidades);
        model.addAttribute("listCategorias", listCategorias);        
        model.addAttribute("obj", obj);
        return "/produto/update";
    }

    @PostMapping("/update")
    public String update(
        @Valid Produto obj, 
        @RequestParam("image") MultipartFile file,
        BindingResult bindingResult
    )
    {
        final Path directory = Paths.get(UPLOAD_DIR);


        if (file != null && !file.isEmpty()) {
            try {

                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
               
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, file.getBytes());
                obj.setImagem(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

        if (bindingResult.hasErrors()) {
            
            return "produto/update";
        }

        service.update(obj);
        return "redirect:/produtos";
    }
}
