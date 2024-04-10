package com.example.ecommerce.dto.produto;

public record ProdutoRequestDTO(String nome, String descricao, Integer preco, String foto, int quantidade) {
}
