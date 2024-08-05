package com.example.ecommerce.repository;

import com.example.ecommerce.domain.venda_produto.VendaProduto;
import com.example.ecommerce.domain.venda_produto.VendaProdutoID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendaProdutoRepository extends JpaRepository<VendaProduto, VendaProdutoID> {

    /*Se usarmos a nova classe criada, VendaProdutoID, como a classe de ID a ser passada para o repository,
      não precisamos nos preocupar em montar uma query para VendaProdutoID, o método padrão de 'findById()'
      já vai esperar VendaProdutoID como argumento
    Optional<VendaProduto> findByVendaProdutoID(VendaProdutoID vendaProdutoID);

    */
}
