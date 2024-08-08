package com.example.ecommerce.domain.venda_produto;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "venda_produto")
@Entity(name = "VendaProduto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaProduto {

    /*
    No link você pode encontrar mais info. sobre a implementação de chaves compostas utilizando o JPA.
    https://jpa-buddy.com/blog/the-ultimate-guide-on-composite-ids-in-jpa-entities/

    Resumidamente, criamos uma classe dedicada para servir de ID composto, anotamos ela com @EmbeddedID e referenciamos,
    nos campos que deveriam ser as chaves primárias, qual chave é qual.

    No exemplo abaixo temos:
        VendaProdutoID é a classe que representa a chave composta desta entidade.
            Nela temos 2 valores/colunas:

            vendaID e produtoID

        Esses valores devem ser "linkados" com as colunas as quais elas representam NESSA entidade
            - A coluna venda_id deve se associar ao valor vendaID da chave composta
            - A coluna produto_id deve se associar ao valor produtoID da chave composta

            Isso é feito por meio da annotation @MapsId("nome_do_atributo")

        Essa annotation presume um @EmbeddedID e busca na estrutura interna da
        classe anotada o atributo passado, para então associá-lo com a coluna nessa entidade e formar a chave


    Essa abordagem de chave composta é vantajosa pois torna prática a consulta de uma entrada especifica em venda_produto,
    uma vez que podemos, via repository, adicionar um método que consulta o banco utilizando uma instancia da classe
    VendaProdutoID, ao invés do ID simples convencional. (Em oposição à ter que usar uma consulta utilizando os 2 valores
    separados)
    */
    @EmbeddedId
    private VendaProdutoID id = new VendaProdutoID();

    @ManyToOne
    @MapsId("vendaID")
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @MapsId("produtoID")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;
}
