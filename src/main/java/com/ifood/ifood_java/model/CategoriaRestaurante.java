/* 

package com.ifood.ifood_java.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity                                         
public class CategoriaRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String imagem;

    public CategoriaRestaurante() {}

    public CategoriaRestaurante(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getImagem() { return imagem; }

    public void setNome(String nome) { this.nome = nome; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public void setHabilitado(Boolean true1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setHabilitado'");
    }

    public Object getDescricao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescricao'");
    }

    public void setDescricao(Object descricao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDescricao'");
    }
    
}
*/