package com.ifood.ifood_java.entity.restaurante;

import com.ifood.ifood_java.entity.categoria.CategoriaRestaurante;
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
public class Restaurante {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_restaurante")
   private Long idRestaurante;

   @Column(name = "nome")
   private String nome;

   @Column(name = "telefone")
   private String telefone;

   @Column(name = "cnpj")
   private String cnpj;

   @Column(name = "raio de entrega")
   private String raio_entrega;

   @OneToOne
   @JoinColumn(name = "id_usuario", nullable = false) 
   private Usuario usuario;

   @ManyToOne
   @JoinColumn(name = "categoria_id")
   private CategoriaRestaurante categoria;

}
