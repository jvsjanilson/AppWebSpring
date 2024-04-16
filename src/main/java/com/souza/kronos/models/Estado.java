package com.souza.kronos.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "Estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
    
    @Column
    @NotBlank
    @Size(min = 3, max = 30)
    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pais pais;

    @Column(unique = true)
    @NotNull
    private Integer codigo;


    public String toString() 
    { 
        return this.uf;
    } 
}
