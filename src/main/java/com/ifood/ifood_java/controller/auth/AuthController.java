package com.ifood.ifood_java.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.entity.usuario.UsuarioRequest;
import com.ifood.ifood_java.infra.security.TokenService;
import com.ifood.ifood_java.repository.UsuarioRepository;
import com.ifood.ifood_java.service.auth.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authServive;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioRequest request){
        String resultado = authServive.registrarUsuario(request);
        return ResponseEntity.ok(resultado);
    }

@PostMapping("/login")
public ResponseEntity<ResponseLoginRequest> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authServive.login(request));
}
}
