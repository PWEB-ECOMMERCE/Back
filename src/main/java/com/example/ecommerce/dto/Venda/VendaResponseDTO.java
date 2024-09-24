package com.example.ecommerce.dto.Venda;

import com.example.ecommerce.domain.venda.Venda;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

public record VendaResponseDTO(int id, LocalDate data_hora, String usuario_id) {
    public VendaResponseDTO (Venda venda) {
        this(
                venda.getId(),
                venda.getData_hora(),
                venda.getUsuario().getId()
        );
    }
}