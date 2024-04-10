package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.usuario.DetalhesUsuario;
import com.example.ecommerce.dto.usuario.UsuarioIdDTO;
import com.example.ecommerce.dto.usuario.UsuarioRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios(){
        List<UsuarioResponseDTO> dadosBasicosUsuarios = this.usuarioService.getDadosBasicosUsuarios();
        return ResponseEntity.ok().body(dadosBasicosUsuarios);
    }
    @GetMapping("/detailed")
    public ResponseEntity<List<DetalhesUsuario>> getAllDetailedUsuarios(){
        List<DetalhesUsuario> dadosDetalhados = this.usuarioService.getAllDetalhesUsuarios();
        return ResponseEntity.ok().body(dadosDetalhados);
    }

    @GetMapping("/{usuarioID}")
    public ResponseEntity<UsuarioResponseDTO> getUsuario(@PathVariable String usuarioID){
        UsuarioResponseDTO dadosUsuario = this.usuarioService.getDadosUsuario(usuarioID);
        return ResponseEntity.ok().body(dadosUsuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioIdDTO> registerUsuario(@RequestBody UsuarioRequestDTO newUserData, UriComponentsBuilder uriComponentsBuilder){
        UsuarioIdDTO usuarioRegistradoID = this.usuarioService.registerUsuario(newUserData);

        URI usuarioURI = uriComponentsBuilder.path("/usuarios/{usuarioID}").buildAndExpand(usuarioRegistradoID.usuarioUUID()).toUri();

        return ResponseEntity.created(usuarioURI).body(usuarioRegistradoID);
    }

    @DeleteMapping("/{usuarioID}")
    public ResponseEntity<UsuarioResponseDTO> deleteUsuario(@PathVariable String usuarioID){
        UsuarioResponseDTO usuarioDeletado = this.usuarioService.deleteUsuario(usuarioID);
        return ResponseEntity.ok().body(usuarioDeletado);
    }
}
