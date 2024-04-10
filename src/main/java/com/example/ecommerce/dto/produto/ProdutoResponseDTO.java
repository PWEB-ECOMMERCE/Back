package com.example.ecommerce.dto.produto;

import com.example.ecommerce.domain.produto.Produto;

public record ProdutoResponseDTO(Integer id, String nome, String descricao, Integer preco, String foto, int quantidade) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getImagemUrl(), produto.getQuantidade());
    }
}
