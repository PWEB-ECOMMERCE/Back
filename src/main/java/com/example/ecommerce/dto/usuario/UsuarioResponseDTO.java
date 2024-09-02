package com.example.ecommerce.dto.usuario;

import com.example.ecommerce.domain.usuario.UserRole;

public record UsuarioResponseDTO(
        String id,
        String nome,
        String endereco,
        String email,
        String username,
        boolean admin,
        UserRole role
) { }
