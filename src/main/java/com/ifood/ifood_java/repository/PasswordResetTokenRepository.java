package com.ifood.ifood_java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifood.ifood_java.entity.senha.PasswordResetToken;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);
}