package com.example.ecommerce.domain.venda;

import com.example.ecommerce.domain.produtos_vendidos.ProdutosVendidos;
import com.example.ecommerce.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Table(name = "venda")
@Entity(name = "venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp data_hora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "ids_produtos_vendidos")
    private List<Integer> ids_produtos_vendidos;

    @Column(name = "valor_total")
    private Integer valorTotal;

}
