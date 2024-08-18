package com.example.ecommerce.dto.categoria;

import com.example.ecommerce.domain.categoria.Categoria;

public record CategoriaResponseDTO(int id, String descricao) {
    public CategoriaResponseDTO(Categoria categoria){
        this(categoria.getId(), categoria.getDescricao());
    }
}
