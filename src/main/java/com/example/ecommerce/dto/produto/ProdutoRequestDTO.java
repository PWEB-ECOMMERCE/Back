package com.example.ecommerce.dto.produto;

import com.example.ecommerce.domain.categoria.Categoria;

public record ProdutoRequestDTO(String nome, String descricao, Integer preco, String foto, int quantidade, Categoria categoria) {
}
