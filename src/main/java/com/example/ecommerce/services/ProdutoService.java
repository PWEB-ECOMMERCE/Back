package com.example.ecommerce.services;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.dto.produto.ProdutoRequestDTO;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoResponseDTO criarProdutoResponseDTo(Produto novoProduto) {
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(
                novoProduto.getId(),
                novoProduto.getNome(),
                novoProduto.getCategoria(),
                novoProduto.getDescricao(),
                novoProduto.getPreco(),
                novoProduto.getImagemUrl(),
                novoProduto.getQuantidade()
        );
        return produtoResponseDTO;
    }

    public ProdutoResponseDTO createProduct(ProdutoRequestDTO produtoRequestDTO) {
        Produto novoProduto = new Produto();
                novoProduto.setNome(produtoRequestDTO.nome());
                novoProduto.setCategoria(produtoRequestDTO.categoria());
                novoProduto.setDescricao(produtoRequestDTO.descricao());
                novoProduto.setPreco(produtoRequestDTO.preco());
                novoProduto.setImagemUrl(produtoRequestDTO.foto());
                novoProduto.setQuantidade(produtoRequestDTO.quantidade());

        this.produtoRepository.save(novoProduto);


        return criarProdutoResponseDTo(novoProduto);
    }

    public ProdutoResponseDTO retornaProduto(int id){
        Produto novoProduto = this.produtoRepository.findById(id);

        return criarProdutoResponseDTo(novoProduto);
    }

    public ProdutoResponseDTO atualizaProduto(int id, ProdutoRequestDTO edited) {
        Produto existingProduto = this.produtoRepository.findById(id);
        if(existingProduto == null) throw  new RuntimeException("Produto não encontrado");


        if (edited.nome() != null) {
            existingProduto.setNome(edited.nome());
        }

        if (edited.descricao() != null) {
            existingProduto.setDescricao(edited.descricao());
        }
        if (edited.preco() != null) {
            existingProduto.setPreco(edited.preco());
        }
        if (edited.foto() != null) {
            existingProduto.setImagemUrl(edited.foto());
        }
        if (edited.quantidade() != 0) {
            existingProduto.setQuantidade(edited.quantidade());
        }
        if (edited.categoria() != null) {
            existingProduto.setCategoria(edited.categoria());
        }

        Produto novoProduto = produtoRepository.save(existingProduto);

        return criarProdutoResponseDTo(novoProduto);
    }

    public ProdutoResponseDTO deletaProduto(int id) {
        Produto produto = this.produtoRepository.findById(id);
        this.produtoRepository.delete(produto);

        return criarProdutoResponseDTo(produto);
    }
}
