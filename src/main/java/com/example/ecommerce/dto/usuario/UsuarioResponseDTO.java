package com.example.ecommerce.dto.usuario;

public record UsuarioResponseDTO(
        String id,
        String nome,
        String endereco,
        String email
) { }
