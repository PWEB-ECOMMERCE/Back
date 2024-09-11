package com.example.ecommerce.dto.venda;

import com.example.ecommerce.domain.produtos_vendidos.ProdutosVendidos;

import java.util.List;

public record ProdutosVendidosDTO(String userId, List<ProdutosVendidos> produtosVendidos) {
}
