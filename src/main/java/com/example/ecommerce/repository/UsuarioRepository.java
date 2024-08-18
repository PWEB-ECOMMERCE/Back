package com.example.ecommerce.repository;

import com.example.ecommerce.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    //isso é o que faz o spring conseguir realizar o login, não tirar nem a pau
    UserDetails findByLogin(String login);
    //Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findByEmail(String email);
}
