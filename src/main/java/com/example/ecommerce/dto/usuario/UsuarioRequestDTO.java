package com.example.ecommerce.dto.usuario;

import org.springframework.beans.factory.annotation.Value;

public record UsuarioRequestDTO(
        String nome,
        String email,
        String endereco,
        String login,
        String senha,

        @Value("${some.key:false}")
        boolean admin
) { }
