package com.ifood.ifood_java.controller.perfil;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ifood.ifood_java.entity.usuario.UsuarioRequest;
import com.ifood.ifood_java.repository.UsuarioRepository;
import com.ifood.ifood_java.service.perfil.PerfilService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfil")
@CrossOrigin
public class PerfilController {

    @Autowired
    private PerfilService perfilService;


    @GetMapping
    public ResponseEntity<MostrarPerfilRequest>pegarPerfil() {
        try {
            MostrarPerfilRequest perfil = perfilService.pegarPerfil();
            return ResponseEntity.ok(perfil);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PatchMapping
    public ResponseEntity<?> atualizarPerfil(@Valid @RequestBody AtualizarPerfilRequest request) {
        try {
            AtualizarPerfilRequest atualizado = perfilService.atualizarPerfil(request);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}