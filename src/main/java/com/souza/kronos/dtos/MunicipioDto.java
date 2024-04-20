package com.souza.kronos.dtos;

import com.souza.kronos.models.Municipio;

public record MunicipioDto(Long id, String descricao, String uf) {
    public MunicipioDto(Municipio municipio) {
        this(municipio.getId(), municipio.getDescricao(), municipio.getEstado().getUf());
    }
}
