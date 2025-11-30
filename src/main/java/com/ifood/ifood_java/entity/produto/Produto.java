package com.ifood.ifood_java.entity.produto;
import com.ifood.ifood_java.entity.categoria.CategoriaProduto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaProduto categoria;
    
}
