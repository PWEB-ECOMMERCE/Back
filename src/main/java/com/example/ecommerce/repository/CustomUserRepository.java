package com.example.ecommerce.repository;

import com.example.ecommerce.domain.usuario.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomUserRepository {

    //Essa assinatura existe pra deixar o userDetails pegar o login do jeito que ele quer
    Usuario retornaUsuarioPorLogin(String login);
}
