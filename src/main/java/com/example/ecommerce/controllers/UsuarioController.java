package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.usuario.DetalhesUsuario;
import com.example.ecommerce.dto.usuario.UsuarioIdDTO;
import com.example.ecommerce.dto.usuario.UsuarioRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
import com.example.ecommerce.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
/*
A Annotation @CrossOrigin permite que essa rota seja acessada por serviços que rodem em uma
porta/máquina distinta da utilizada pela API.

Mais especificamente, atributo 'origins' dita que rotas serão permitidas a fazer essas requisições.
O valor atual (30/07/2024) está "*", o que indica que qualquer requisição feita à API será permitida.
Idealmente, também por questões de segurança, no lugar de "*" teríamos nosso próprio domínio com as
rotas que desejamos permitir o acesso.

Além disso, as configurações de CORS (Cross Origin Refs.) podem ser feitas globalmente, assim como os
filtros de rota (mesmo conceito explicado pelo Prof. Léo na disciplina).

Mais informações sobre CORS:

    1. https://spring.io/guides/gs/rest-service-cors
    2. https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS

*/
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
    public ResponseEntity<UsuarioResponseDTO> getUsuario(@PathVariable String usuarioID, HttpServletRequest request){
        UsuarioResponseDTO dadosUsuario = this.usuarioService.getDadosUsuario(usuarioID);
        HttpSession session = request.getSession(false);
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
    @PatchMapping("/{usuarioID}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(
            @PathVariable("usuarioID") String usuarioID,
            @RequestBody UsuarioRequestDTO updatedUser) {
        UsuarioResponseDTO usuario = usuarioService.updateUsuario(usuarioID, updatedUser);
        return ResponseEntity.ok(usuario);
    }
}
