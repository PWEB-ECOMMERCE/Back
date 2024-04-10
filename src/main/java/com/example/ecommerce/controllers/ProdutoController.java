package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.dto.produto.ProdutoRequestDTO;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;
    @GetMapping
    public List<ProdutoResponseDTO> getAll(){
        List<ProdutoResponseDTO> listaProduto = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return listaProduto;
    }

//    //Deve ser movido pra classe de service dedicada
//    @PostMapping
//    public void saveProduto(@RequestBody ProdutoRequestDTO data){
//        Produto produtoData = new Produto();
//        produtoData.setNome(data.nome());
//        produtoData.setDescricao(data.descricao());
//        produtoData.setPreco(data.preco());
//        produtoData.setFoto(data.foto());
//        produtoData.setQuantidade(data.quantidade());
//
//        repository.save(produtoData);
//    }
}
