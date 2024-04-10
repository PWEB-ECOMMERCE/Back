package com.example.ecommerce.domain.usuario.exceptions;

public class UserLoginAlreadyRegistered extends RuntimeException{

    private static final String STANDARD_MESSAGE = "Usuario com o login '@login@' já existe.";

    public UserLoginAlreadyRegistered(String invalidLogin){
        super(STANDARD_MESSAGE.replace("@login@", invalidLogin));
    }
}
