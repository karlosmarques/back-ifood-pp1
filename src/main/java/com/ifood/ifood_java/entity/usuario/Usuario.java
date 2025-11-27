package com.ifood.ifood_java.entity.usuario;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuarios")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_usuario")
   private Long idUsuario;
   
   @Column(unique = true, nullable = false)
   private String email;
   
   @JsonIgnore
   @Column(nullable = false)
   private String password;
   
   @Column(nullable = false)
   private String nome;
   
   @Column(unique = true, nullable = false, length = 11)
   private String cpf;
   
   @Column(name = "dt_nascimento") 
   private LocalDate dtNascimento;  
   
   @Column(name = "fone_celular", length = 15) 
   private String foneCelular;  
}