package com.example.ecommerce.dto.produto;

import com.example.ecommerce.domain.categoria.Categoria;
import com.example.ecommerce.domain.produto.Produto;

public record ProdutoResponseDTO(Integer id, String nome, Categoria categoria, String descricao, Integer preco, String foto, int quantidade) {
    public ProdutoResponseDTO(Produto produto){
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getImagemUrl(),
                produto.getQuantidade()
        );
    }
}
