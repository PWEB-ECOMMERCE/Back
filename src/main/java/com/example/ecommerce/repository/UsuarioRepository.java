package com.example.ecommerce.repository;

import com.example.ecommerce.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findByEmail(String email);
}
