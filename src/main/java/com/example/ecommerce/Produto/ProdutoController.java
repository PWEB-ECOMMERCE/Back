package com.example.ecommerce.Produto;

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
    @PostMapping
    public void saveProduto(@RequestBody ProdutoRequestDTO data){
        Produto produtoData = new Produto(data);
        repository.save(produtoData);
        return;
    }
}
