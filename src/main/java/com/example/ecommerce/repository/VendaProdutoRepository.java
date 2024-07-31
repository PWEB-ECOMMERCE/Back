package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda_produto.Venda_produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaProdutoRepository extends JpaRepository<Venda_produto, Integer> {
}
