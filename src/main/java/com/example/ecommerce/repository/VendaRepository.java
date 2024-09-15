package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.domain.venda.VendasDiaInterface;
import com.example.ecommerce.dto.Venda.RelatorioDTO;
import com.example.ecommerce.dto.relatorio.ComprasClientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByUsuarioId(String id);

    /*
    @Query(value = "SELECT id, data_hora, usuario_id, COUNT(*) AS num FROM venda GROUP BY id ORDER BY num DESC", nativeQuery = true)
    List<Venda> finda();

     */

    @Query(value = "SELECT usuario_id, COUNT(usuario_id) AS num FROM venda WHERE data_hora >= ? AND data_hora  <= ? GROUP BY usuario_id ORDER BY num DESC ", nativeQuery = true)
    List<ComprasInterface> finda(LocalDate data, LocalDate endData);

    /*
    @Query(value = "SELECT * FROM venda WHERE " +
            "data_hora >= ? " +
            "and data_hora <= ?", nativeQuery = true)
    List<Venda> vendasPortData(Timestamp startTime, Timestamp endTime);

     */

    @Query(value = "SELECT data_hora FROM venda GROUP BY data_hora", nativeQuery = true)
    List<String> findByData_hora(LocalDate data);

    @Query(value = "select venda.*, venda_produto.produto_id, venda_produto.quantidade from venda inner join venda_produto on venda.id = venda_produto.venda_id where data_hora >= ? AND data_hora  <= ?", nativeQuery = true)
    List<VendasDiaInterface> findVendasPeriodoTempo(LocalDate data, LocalDate endData);
}
