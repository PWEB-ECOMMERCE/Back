package com.example.ecommerce.domain.venda_produto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"vendaID", "produtoID"})
public class VendaProdutoID implements Serializable {

    @Column(name = "venda_id")
    private int vendaID;

    @Column(name = "produto_id")
    private int produtoID;
}
