package com.example.ecommerce.repository;

import com.example.ecommerce.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    public Categoria findById(int id);

    public Categoria findByDescricao(String descricao);
}
