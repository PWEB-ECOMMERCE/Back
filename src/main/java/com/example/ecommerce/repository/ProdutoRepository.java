package com.example.ecommerce.repository;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Produto findById(int id);

    @Query(value = "SELECT * FROM produto WHERE quantidade < 1 ORDER BY descricao", nativeQuery = true)
    List<Produto> produtosEsgotados();
}
