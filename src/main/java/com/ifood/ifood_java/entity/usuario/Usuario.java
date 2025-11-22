package com.ifood.ifood_java.entity.usuario;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios" )
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Usuario {
   @Id
   @GeneratedValue(strategy =GenerationType.IDENTITY )
   private Long id_usuario;

   private String email;

   private String password;

   private String nome;

   private String cpf;

   private LocalDate dt_dascimento;

   private String fone_celular;


}
