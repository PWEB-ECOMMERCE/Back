package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.domain.venda.VendasDiaInterface;
import com.example.ecommerce.dto.Venda.RelatorioDTO;
import com.example.ecommerce.dto.relatorio.ComprasClientes;
import com.example.ecommerce.dto.relatorio.VendasPorTempoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
    List<Venda> findAllByUsuarioId(String id);

    @Query(value = "SELECT usuario_id, COUNT(usuario_id) AS num FROM venda WHERE data_hora >= ? AND data_hora  <= ? GROUP BY usuario_id ORDER BY num DESC ", nativeQuery = true)
    List<ComprasInterface> finda(LocalDate data, LocalDate endData);

    @Query(value = "SELECT data_hora FROM venda GROUP BY data_hora", nativeQuery = true)
    List<String> findByData_hora(LocalDate data);

    @Query(value = "select data_hora, sum(vp.quantidade * p.preco) as valor_total from venda as v, venda_produto as vp, produto as p where v.id = vp.venda_id and vp.produto_id = p.id and v.data_hora >= ? and v.data_hora <= ? group by v.data_hora", nativeQuery = true)
    List<VendasDiaInterface> findVendasPeriodoTempo(LocalDate data, LocalDate endData);
}
