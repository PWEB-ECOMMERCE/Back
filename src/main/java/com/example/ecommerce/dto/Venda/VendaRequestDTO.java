package com.example.ecommerce.dto.Venda;

import java.util.List;

public record VendaRequestDTO(String userId, List<CarrinhoDTO> itensCarrinho) {
}
