package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Integer> {
}
