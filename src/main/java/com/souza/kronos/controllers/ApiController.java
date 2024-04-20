package com.souza.kronos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.souza.kronos.dtos.MunicipioDto;
import com.souza.kronos.services.MunicipioService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    MunicipioService municipioService;

    @GetMapping("/municipios/{id}")
    public ResponseEntity<List<MunicipioDto>> findByEstadoId(@PathVariable() Long id)
    {
        var list = municipioService.findByEstadoId(id).stream().map(MunicipioDto::new).toList();

        return ResponseEntity.ok(list);
    }

}
