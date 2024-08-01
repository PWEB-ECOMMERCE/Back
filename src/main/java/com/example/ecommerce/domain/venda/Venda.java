package com.example.ecommerce.domain.venda;

import com.example.ecommerce.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "venda")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Timestamp data_hora;
    /*
    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario usuario;

     */
}
