package com.example.ecommerce.Produto;

public record ProdutoResponseDTO(Long id, String nome, String descricao, Double preco, String foto, int quantidade) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getFoto(), produto.getQuantidade());
    }
}
