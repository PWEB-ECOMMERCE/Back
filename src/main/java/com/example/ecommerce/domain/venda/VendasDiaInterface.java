package com.example.ecommerce.domain.venda;

import java.time.LocalDate;

public interface VendasDiaInterface {
    int getId();
    LocalDate getData_hora();
    String getUsuario_id();
    int getProduto_id();
    int getQuantidade();
}
