package com.ifood.ifood_java.service.perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ifood.ifood_java.controller.perfil.AtualizarPerfilRequest;
import com.ifood.ifood_java.controller.perfil.MostrarPerfilRequest;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.entity.usuario.UsuarioRequest;
import com.ifood.ifood_java.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class PerfilService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


    public MostrarPerfilRequest pegarPerfil() {
       
        Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );
        
         Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new MostrarPerfilRequest(
            usuario.getNome(),
            usuario.getEmail()
        );
        
    }
    @Transactional
    public AtualizarPerfilRequest atualizarPerfil(AtualizarPerfilRequest request) {
    
       Long userId = Long.parseLong(
        SecurityContextHolder.getContext().getAuthentication().getName()
    );
        
         Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    if (request.getNome() != null) {
        usuario.setNome(request.getNome());
    }
    if (request.getEmail() != null) {
        usuario.setEmail(request.getEmail());
    }
    
    Usuario atualizado = usuarioRepository.save(usuario);
    
    return new AtualizarPerfilRequest(
        atualizado.getNome(),
        atualizado.getEmail()
    );
}
}