package com.example.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.services.UsuarioService;

import lombok.RequiredArgsConstructor;

import com.example.ecommerce.dto.auth.LoginRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
          UsuarioResponseDTO user = usuarioService.authenticateUsuario(loginRequest.username(), loginRequest.password());
          return ResponseEntity.ok(user);
        } catch (Exception e) {
          return ResponseEntity.status(401).body(e.toString());
        }

    }

    // @PostMapping("/logout")
    // public ResponseEntity<?> logout() {
    //     // Implement logout logic, invalidate token or session
    //     authService.logout();
    //     return ResponseEntity.ok().build();
    // }
}
