package com.example.ecommerce.Produto;

public record ProdutoRequestDTO(String nome, String descricao, Double preco, String foto, int quantidade) {
}
