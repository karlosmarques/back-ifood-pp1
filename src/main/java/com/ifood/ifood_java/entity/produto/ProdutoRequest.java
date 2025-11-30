package com.ifood.ifood_java.entity.produto;
import lombok.Data;

@Data
public class ProdutoRequest {

    private String nome;

    private String descricao;

    private Double preco;
    
    private Long categoriaId;
    
}
