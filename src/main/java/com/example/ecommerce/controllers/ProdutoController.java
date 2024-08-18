package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.dto.produto.ProdutoRequestDTO;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAll(){
        List<ProdutoResponseDTO> listaProduto = produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
        return ResponseEntity.ok().body(listaProduto);
    }

    @GetMapping("/esp/{produtoID}")
    public ResponseEntity<ProdutoResponseDTO> getProduto(@PathVariable int produtoID){
        ProdutoResponseDTO produtoResponseDTO = produtoService.retornaProduto(produtoID);

        return ResponseEntity.ok().body(produtoResponseDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> createProduct(@RequestBody ProdutoRequestDTO produtoRequestDTO, UriComponentsBuilder uriComponentsBuilder){
        ProdutoResponseDTO produtoResponseDTO = this.produtoService.createProduct(produtoRequestDTO);
        URI produtoURI = uriComponentsBuilder.path("produtos/esp/{produtoID}").buildAndExpand(produtoResponseDTO).toUri();

        return ResponseEntity.created(produtoURI).body(produtoResponseDTO);
    }

    @PatchMapping("/esp/{produtoID}")
    public ResponseEntity<ProdutoResponseDTO> updateProduto(@PathVariable int produtoID, @RequestBody ProdutoRequestDTO updatedProduto) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.atualizaProduto(produtoID, updatedProduto);

        return ResponseEntity.ok(produtoResponseDTO);
    }

    @DeleteMapping("/esp/{produtoID}")
    public ResponseEntity<ProdutoResponseDTO> deleteUsuario(@PathVariable int produtoID){
        ProdutoResponseDTO produtoDeletado = this.produtoService.deletaProduto(produtoID);
        return ResponseEntity.ok().body(produtoDeletado);
    }
}
