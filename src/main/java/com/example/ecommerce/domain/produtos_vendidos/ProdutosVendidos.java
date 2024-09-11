package com.example.ecommerce.domain.produtos_vendidos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "produtos_vendidos")
@Table(name = "produtos_vendidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutosVendidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int prodId;

    @Column
    private int qtd;

    public ProdutosVendidos(int prodId, int qtd){
        this.prodId = id;
        this.qtd = qtd;
    }


}
