package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.dto.produto.ProdutoRequestDTO;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//Vide UsuarioController para mais info sobre a Annotation @CrossOrigin
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public String getAll(){
        //List<ProdutoResponseDTO> listaProduto = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return "Oi tudo bem?";
    }

    @GetMapping("/bloca")
    public String uia(){
        //List<ProdutoResponseDTO> listaProduto = repository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return "Entrou na rota bloqueada, MUHUAHUAHHahahahha";
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
