package com.ifood.ifood_java.entity.produtos;

import java.math.BigDecimal;

import com.ifood.ifood_java.entity.restaurante.Restaurante;
import com.ifood.ifood_java.entity.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produtos {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_produto")
   private Long idProduto;

   @Column(name = "nome")
   private String nome;

   @Column(name = "descricao")
   private String descricao;

   @Column(name = "preco")
   private BigDecimal preco;

   @Column(name = "ativo")
   private Boolean ativo;

   @ManyToOne
   @JoinColumn(name = "id_restaurante", nullable = false)
   private Restaurante restaurante;

}
