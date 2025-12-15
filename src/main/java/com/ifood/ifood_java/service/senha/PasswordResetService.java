package com.ifood.ifood_java.service.senha;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ifood.ifood_java.entity.senha.PasswordResetToken;
import com.ifood.ifood_java.entity.usuario.Usuario;
import com.ifood.ifood_java.repository.PasswordResetTokenRepository;
import com.ifood.ifood_java.repository.UsuarioRepository;

@Service
public class PasswordResetService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Solicitar reset
    public void forgotPassword(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiracao(LocalDateTime.now().plusMinutes(30));

        tokenRepository.save(token);

        //  Simulação de envio de email
        System.out.println("LINK DE RESET:");
        System.out.println("http://localhost:3000/resetar-senha?token=" + token.getToken());
    }

    // Resetar senha
    public void resetarSenha(String tokenStr, String novaSenha) {

        PasswordResetToken token = tokenRepository.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (token.getExpiracao().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = token.getUsuario();
        usuario.setPassword(passwordEncoder.encode(novaSenha));

        usuarioRepository.save(usuario);
        tokenRepository.delete(token);
    }
}
