package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.dto.Venda.RelatorioDTO;
import com.example.ecommerce.dto.relatorio.ComprasClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByUsuarioId(String id);

    /*
    @Query(value = "SELECT id, data_hora, usuario_id, COUNT(*) AS num FROM venda GROUP BY id ORDER BY num DESC", nativeQuery = true)
    List<Venda> finda();

     */

    @Query(value = "SELECT usuario_id, COUNT(usuario_id) AS num FROM venda GROUP BY usuario_id ORDER BY num DESC", nativeQuery = true)
    List<ComprasInterface> finda();

}
