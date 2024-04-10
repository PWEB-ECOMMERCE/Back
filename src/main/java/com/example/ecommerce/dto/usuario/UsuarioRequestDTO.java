package com.example.ecommerce.dto.usuario;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String endereco,
        String login,
        String senha
) { }
