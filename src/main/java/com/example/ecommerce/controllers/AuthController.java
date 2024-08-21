package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.dto.usuario.UsuarioIdDTO;
import com.example.ecommerce.dto.usuario.UsuarioRequestDTO;
import com.example.ecommerce.repository.CustomUserRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import com.example.ecommerce.services.AuthorizationService;
import com.example.ecommerce.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

//import com.example.ecommerce.services.UsuarioService;

import com.example.ecommerce.dto.auth.LoginRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CustomUserRepository customUserRepository;
    @Autowired
    SecurityContext securityContext;
    @Autowired
    SecurityContextRepository securityContextRepository;

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession httpSession,
                                HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        SecurityContextHolder.getContext().setAuthentication(auth);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, httpServletRequest, httpServletResponse);

        Usuario usuario = customUserRepository.retornaUsuarioPorLogin(loginRequestDTO.username());
        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEndereco(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.isAdministrador()
        );
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioRequestDTO newUserData, UriComponentsBuilder uriComponentsBuilder) {

        UsuarioIdDTO usuarioRegistradoID = this.usuarioService.registerUsuario(newUserData);
        URI usuarioURI = uriComponentsBuilder.path("/usuarios/esp/{usuarioID}").buildAndExpand(usuarioRegistradoID.usuarioUUID()).toUri();

        return ResponseEntity.created(usuarioURI).body(usuarioRegistradoID);
    }

}
