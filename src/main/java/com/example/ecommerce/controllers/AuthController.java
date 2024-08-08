package com.example.ecommerce.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.services.UsuarioService;

import lombok.RequiredArgsConstructor;

import com.example.ecommerce.dto.auth.LoginRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
          UsuarioResponseDTO user = usuarioService.authenticateUsuario(loginRequest.username(), loginRequest.password());
          //aqui a sessao é criada e o id do usuario atribuido como valor do atributo "usuario" da sessão
          HttpSession session = request.getSession();
          session.setAttribute("usuario", user.id());
          return ResponseEntity.ok(user);
        } catch (Exception e) {
          return ResponseEntity.status(401).body(e.getMessage().toString());
        }

    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
    }
    // @PostMapping("/logout")
    // public ResponseEntity<?> logout() {
    //     // Implement logout logic, invalidate token or session
    //     authService.logout();
    //     return ResponseEntity.ok().build();
    // }
}
