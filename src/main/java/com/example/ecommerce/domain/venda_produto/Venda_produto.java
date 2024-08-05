package com.example.ecommerce.domain.venda_produto;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "venda_produto")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda_produto {

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;
}
