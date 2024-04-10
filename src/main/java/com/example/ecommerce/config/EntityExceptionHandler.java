package com.example.ecommerce.config;

import com.example.ecommerce.domain.usuario.exceptions.UserEmailAlreadyRegistered;
import com.example.ecommerce.domain.usuario.exceptions.UserLoginAlreadyRegistered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //Indica pro Spring qual é a classe responsável por tratar exceções
public class EntityExceptionHandler {

    @ExceptionHandler(UserEmailAlreadyRegistered.class)
    public ResponseEntity handleAlreadyRegisteredEmail(UserEmailAlreadyRegistered exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(UserLoginAlreadyRegistered.class)
    public ResponseEntity handleAlreadyRegisteredLogin(UserLoginAlreadyRegistered exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
