package com.souza.kronos.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Contato")
public class Contato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @Column
    @NotBlank
    @Max(60)
    
    private String nome;

    @Size(max = 60)
    private String nomeFantasia;

    @Size(max = 14)
    private String documento;

    @Size(max = 60)
    private String endereco;

    @Size(max = 10)
    private String numero;

    @Size(max = 8)
    private String cep;

    @Size(max = 120)
    private String bairro;

    @Size(max = 120)
    private String complemento;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Estado estado;

    @ManyToOne(cascade = CascadeType.ALL)
    private Municipio municipio;

    public String toString() 
    { 
        return this.nome;
    } 
}