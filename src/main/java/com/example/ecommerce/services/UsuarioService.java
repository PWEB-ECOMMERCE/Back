package com.example.ecommerce.services;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.usuario.exceptions.UserEmailAlreadyRegistered;
import com.example.ecommerce.domain.usuario.exceptions.UserLoginAlreadyRegistered;
import com.example.ecommerce.dto.usuario.DetalhesUsuario;
import com.example.ecommerce.dto.usuario.UsuarioIdDTO;
import com.example.ecommerce.dto.usuario.UsuarioRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
import com.example.ecommerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;


    //TODO: mapeador de entity -> DTO
    public List<Usuario> getAllUsuarios(){
        return this.usuarioRepository.findAll();
    }
    public List<UsuarioResponseDTO> getDadosBasicosUsuarios(){
        List<Usuario> allUsuarios = getAllUsuarios();

        List<UsuarioResponseDTO> usuarioResponseDTOs = allUsuarios.stream().map( usuarioAtual -> {
            return new UsuarioResponseDTO(
                    usuarioAtual.getId(),
                    usuarioAtual.getNome(),
                    usuarioAtual.getEndereco(),
                    usuarioAtual.getEmail()
            );
        }).toList();

        return usuarioResponseDTOs;
    }
    public List<DetalhesUsuario> getAllDetalhesUsuarios(){
        List<Usuario> allUsuarios = getAllUsuarios();

        List<DetalhesUsuario> detalhesUsuarios = allUsuarios.stream().map( usuarioAtual -> {
            return new DetalhesUsuario(
                    usuarioAtual.getId(),
                    usuarioAtual.getLogin(),
                    usuarioAtual.isAdministrador(),
                    usuarioAtual.getNome(),
                    usuarioAtual.getEmail(),
                    usuarioAtual.getEndereco()
                    );
        }).toList();

        return detalhesUsuarios;
    }

    public UsuarioResponseDTO getDadosUsuario(String usuarioID){
        Usuario usuario = getUsuario(usuarioID);
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEndereco(), usuario.getEmail());
    }
    private Usuario getUsuario(String usuarioID){
        Optional<Usuario> fetchedUsuario = this.usuarioRepository.findById(usuarioID);

        //Todo: criar classe de exceção para UserNotFoundException
        Usuario usuario = fetchedUsuario.orElseThrow( () -> new RuntimeException("Usuario não encontrado com ID " + usuarioID));

        return usuario;
    }

    public boolean validateUsuario(String login, String email){
        /*
        Dois usuários distintos não devem ter logins ou emails iguais, portanto devemos ter isso em mente ao considerar um usuario válido.

        Para diferenciar as 2 falhas em tempo de execução, serão usadas exceções específicas que podem ser tratadas nas rotinas seguintes
         */
        if (!validLogin(login) || !validEmail(email)){ //Em qualquer caso onde um seja inválido, o usuário é invalido
            return false;
        }
        return true;
    }
    private boolean validLogin(String usuarioLogin){
        Optional<Usuario> fetchedUsuario = this.usuarioRepository.findByLogin(usuarioLogin);
        if (fetchedUsuario.isPresent()){
            throw new UserLoginAlreadyRegistered(usuarioLogin);
        }
        return true;
    }
    private boolean validEmail(String usuarioEmail){
        Optional<Usuario> fetchedUsuario = this.usuarioRepository.findByEmail(usuarioEmail);
        if (fetchedUsuario.isPresent()){
            throw new UserEmailAlreadyRegistered(usuarioEmail);
        }
        return true;
    }

    public UsuarioIdDTO registerUsuario(UsuarioRequestDTO newUserData){
        if (!validateUsuario(newUserData.login(), newUserData.email())){
            return new UsuarioIdDTO("");
        }

        //O usuário é valido, portanto:
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(newUserData.nome());
        novoUsuario.setEmail(newUserData.email());
        novoUsuario.setEndereco(newUserData.endereco());
        novoUsuario.setLogin(newUserData.login());
        novoUsuario.setSenha(newUserData.senha());
        novoUsuario.setAdministrador(false);

        this.usuarioRepository.save(novoUsuario);

        return new UsuarioIdDTO(novoUsuario.getId());
    }

    public UsuarioResponseDTO deleteUsuario(String usuarioID){
        Usuario usuario = this.getUsuario(usuarioID);
        UsuarioResponseDTO deletedUserDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEndereco(),
                usuario.getEmail()
        );

        this.usuarioRepository.delete(usuario);

        return deletedUserDTO;
    }

}
