package com.ifood.ifood_java.entity.usuario;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginRequest {
    
    private    String access_token;
    private    String token_type;
    private    Long id;
    private    String nome;
    private    String email;
    private    String cpf;
    private    LocalDate dtNascimento;
    private    String foneCelular;
}