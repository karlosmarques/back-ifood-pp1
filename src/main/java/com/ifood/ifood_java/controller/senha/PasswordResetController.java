package com.ifood.ifood_java.controller.senha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifood.ifood_java.entity.senha.ForgotPasswordRequest;
import com.ifood.ifood_java.entity.senha.ResetPasswordRequest;
import com.ifood.ifood_java.service.senha.PasswordResetService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        passwordResetService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Email enviado (simulado)");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        passwordResetService.resetarSenha(
                request.getToken(),
                request.getNovaSenha()
        );

        return ResponseEntity.ok("Senha alterada com sucesso");
    }
}
