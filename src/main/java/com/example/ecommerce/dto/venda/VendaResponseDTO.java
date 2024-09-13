package com.example.ecommerce.dto.venda;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.Venda;

import java.sql.Timestamp;
import java.util.List;

public record VendaResponseDTO(int id, Timestamp data_hora, String usuario_id, List<Integer> ids_produtos_vendidos, int valorTotal) {
    public VendaResponseDTO (Venda venda) {
        this(
                venda.getId(),
                venda.getData_hora(),
                venda.getUsuario().getId(),
                venda.getIds_produtos_vendidos(),
                venda.getValorTotal()
        );
    }
}
