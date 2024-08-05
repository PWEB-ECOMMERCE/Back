package com.example.ecommerce.domain.produto;

import com.example.ecommerce.domain.categoria.Categoria;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "produto")
@Entity(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer preco; //Em centavos (float/double rounding/precision error)

    @Column(name = "imagem_url",nullable = false)
    private String imagemUrl;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "cat_id", nullable = false)
    private Categoria categoria;
}
