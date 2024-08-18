package com.example.ecommerce.services;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.usuario.exceptions.UserEmailAlreadyRegistered;
import com.example.ecommerce.domain.usuario.exceptions.UserLoginAlreadyRegistered;
import com.example.ecommerce.dto.usuario.DetalhesUsuario;
import com.example.ecommerce.dto.usuario.UsuarioIdDTO;
import com.example.ecommerce.dto.usuario.UsuarioRequestDTO;
import com.example.ecommerce.dto.usuario.UsuarioResponseDTO;
import com.example.ecommerce.repository.CustomUserRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CustomUserRepository customUserRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;


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
                    usuarioAtual.getEmail(),
                    usuarioAtual.getLogin(),
                    usuarioAtual.isAdministrador()
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
        Usuario usuario = getUsuarioByID(usuarioID);
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEndereco(), usuario.getEmail(), usuario.getLogin(), usuario.isAdministrador());
    }

    private Usuario getUsuarioByID(String usuarioID){
        Optional<Usuario> fetchedUsuario = this.usuarioRepository.findById(usuarioID);

        //Todo: criar classe de exceção para UserNotFoundException
        Usuario usuario = fetchedUsuario.orElseThrow( () -> new RuntimeException("Usuario não encontrado com ID " + usuarioID));

        return usuario;
    }

    private Usuario getUsuarioByEmail(String usuarioEmail){
        Optional<Usuario> fetchedUsuario = this.usuarioRepository.findByEmail(usuarioEmail);

        Usuario usuario = fetchedUsuario.orElseThrow( () -> new RuntimeException("Usuario não encontrado"));

        return usuario;
    }

    private Usuario getUsuarioByLogin(String usuarioLogin){

        //Usuario usuario = fetchedUsuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        try{
            Usuario fetchedUsuario = this.customUserRepository.retornaUsuarioPorLogin(usuarioLogin);
            return fetchedUsuario;
        } catch (RuntimeException e) {
            throw new RuntimeException("Usuário não encontrado");
        }


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
        //Optional<Usuario> fetchedUsuario = this.usuarioRepository.findByLogin(usuarioLogin);
        Usuario fetchedUsuario = this.customUserRepository.retornaUsuarioPorLogin(usuarioLogin);
        if (fetchedUsuario != null){
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
        /*
        if (!validateUsuario(newUserData.login(), newUserData.email())){
            return new UsuarioIdDTO("");
        }
        
         */
        String encryptedPassword = new BCryptPasswordEncoder().encode(newUserData.senha());


        //O usuário é valido, portanto:
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(newUserData.nome());
        novoUsuario.setEmail(newUserData.email());
        novoUsuario.setEndereco(newUserData.endereco());
        novoUsuario.setLogin(newUserData.login());
        novoUsuario.setSenha(encryptedPassword);
        novoUsuario.setAdministrador(newUserData.admin());

        this.usuarioRepository.save(novoUsuario);

        return new UsuarioIdDTO(novoUsuario.getId());
    }

    public UsuarioResponseDTO authenticateUsuario(String usuarioLogin,String senha){
        //Usuario usuario = this.getUsuarioByEmail(usuarioEmail);
        //mudei de email para login
        Usuario usuario = this.getUsuarioByLogin(usuarioLogin);
        if ( usuario != null ){
          if ( BCrypt.checkpw(senha,usuario.getSenha()) ){
            UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEndereco(),
                    usuario.getEmail(),
                    usuario.getLogin(),
                    usuario.isAdministrador()
            );
            return usuarioResponseDTO;
          }
        }
        throw new RuntimeException("Dados incorretos!");
    }


    public UsuarioResponseDTO deleteUsuario(String usuarioID){
        Usuario usuario = this.getUsuarioByID(usuarioID);
        UsuarioResponseDTO deletedUserDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEndereco(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.isAdministrador()
        );

        this.usuarioRepository.delete(usuario);

        return deletedUserDTO;
    }
    public UsuarioResponseDTO updateUsuario(String usuarioID, UsuarioRequestDTO editted){
        Usuario existingUsuario = usuarioRepository.findById(usuarioID)
            .orElseThrow(() -> new RuntimeException("Usuario not found"));

        if (editted.nome() != null) {
            existingUsuario.setNome(editted.nome());
        }

        if (editted.email() != null) {
            existingUsuario.setEmail(editted.email());
        }
        if (editted.endereco() != null) {
            existingUsuario.setEndereco(editted.endereco());
        }
        if (editted.login() != null) {
            existingUsuario.setLogin(editted.login());
        }
        if (editted.senha() != null && editted.senha().length() > 8) {
            existingUsuario.setSenha(BCrypt.hashpw(editted.senha(),BCrypt.gensalt()));
        }
        Usuario user = usuarioRepository.save(existingUsuario);
        return new UsuarioResponseDTO(
            user.getId(),
            user.getNome(),
            user.getEndereco(),
            user.getEmail(),
            user.getLogin(),
            user.isAdministrador()
        );

    }
}
