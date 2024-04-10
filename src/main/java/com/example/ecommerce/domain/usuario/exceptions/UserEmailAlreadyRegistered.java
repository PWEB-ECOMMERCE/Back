package com.example.ecommerce.domain.usuario.exceptions;

public class UserEmailAlreadyRegistered extends RuntimeException{

    private static final String STANDARD_MESSAGE = "O email '@email@' já está registrado.";

    public UserEmailAlreadyRegistered(String invalidEmail){
        super(STANDARD_MESSAGE.replace("@email@", invalidEmail));
    }
}
