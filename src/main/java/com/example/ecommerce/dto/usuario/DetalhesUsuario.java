package com.example.ecommerce.dto.usuario;

public record DetalhesUsuario(
        String id,
        String login,
        boolean adm,
        String nome,
        String email,
        String endereco
) { }
