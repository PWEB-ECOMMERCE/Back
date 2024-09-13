package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("relatorio")
public class RelatorioController {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;




    @GetMapping("/produtosEsgotados")
    public ResponseEntity<List<ProdutoResponseDTO>> retornaProdutosEsgotados(){
        List<ProdutoResponseDTO> produtosEsgotados = produtoRepository.produtosEsgotados().stream().map(ProdutoResponseDTO::new).toList();

        return ResponseEntity.ok().body(produtosEsgotados);
    }
}
