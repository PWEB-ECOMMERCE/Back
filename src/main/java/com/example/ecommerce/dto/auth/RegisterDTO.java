package com.example.ecommerce.dto.auth;

import org.springframework.beans.factory.annotation.Value;

public record RegisterDTO(String nome, String login, String senha, String endereco, String email, @Value("${some.key:false}")
boolean admin) {
}
