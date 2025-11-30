package com.ifood.ifood_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifood.ifood_java.entity.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
