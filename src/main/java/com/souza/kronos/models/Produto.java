package com.souza.kronos.models;

import java.math.BigDecimal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @Size(max = 14)
    @NotBlank
    @Column(nullable = false)
    private String gtin;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Unidade unidade;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Categoria categoria;

    @Column(precision = 12, scale = 2)
    private BigDecimal preco;

    private Integer estoque;

    private String imagem;

    
}
