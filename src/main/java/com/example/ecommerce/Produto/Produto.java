package com.example.ecommerce.Produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produto")
@Entity(name = "produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String foto;
    private int quantidade;
    //falta o id da categoria como FK

    public Produto(ProdutoRequestDTO data){
        this.nome = data.nome();
        this.descricao = data.descricao();
        this.preco = data.preco();
        this.foto = data.foto();
        this.quantidade = data.quantidade();
    }
}
