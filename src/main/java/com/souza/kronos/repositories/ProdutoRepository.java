package com.souza.kronos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.souza.kronos.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
