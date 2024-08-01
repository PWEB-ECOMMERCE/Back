package com.example.ecommerce.domain.categoria;

import com.example.ecommerce.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Table(name = "categoria")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descricao;
}
