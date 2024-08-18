package com.example.ecommerce.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name="usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean administrador;

    public Usuario(String nome, String login, String senha, String endereco, String email, Boolean administrador) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.email = email;
        this.administrador = administrador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
