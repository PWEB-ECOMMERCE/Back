package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.dto.venda.ProdutosVendidosDTO;
import com.example.ecommerce.dto.venda.VendaResponseDTO;
import com.example.ecommerce.repository.VendaRepository;
import com.example.ecommerce.services.ProdutoService;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;


    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> getVendas(){
        List<VendaResponseDTO> vendas = vendaRepository.findAll().stream().map(VendaResponseDTO::new).toList();
        return ResponseEntity.ok().body(vendas);
    }

    @PostMapping
    public void criaVenda(@RequestBody ProdutosVendidosDTO produtosVendidosDTO){

        //ProdutosVendidos produtosVendidos1 = new ProdutosVendidos(produtosVendidosDTO.prodId(), produtosVendidosDTO.qtd());
        //System.out.println(produtosVendidosDTO.produtosVendidos());
        AtomicInteger valor_total = new AtomicInteger();
        List<Integer> listinha = new ArrayList<>();

        produtosVendidosDTO.produtosVendidos().forEach(produto -> listinha.add(produto.getProdId()));
        Usuario usuario = usuarioService.getUsuarioByID(produtosVendidosDTO.userId());
        Produto produtinho = new Produto();

        //listinha.forEach(item -> valor_total.addAndGet(produtoService.retornaProduto(item).preco()));
        for(int i = 0; i < listinha.size(); i++) {
            valor_total.addAndGet(produtoService.retornaProduto(listinha.get(i)).preco() * produtosVendidosDTO.produtosVendidos().get(i).getQtd());
        }
        System.out.println("Valor total: " + valor_total);
        System.out.println(Instant.now());
        System.out.println(listinha);
        System.out.println(usuario.getId());

        Venda novaVenda = new Venda();
        novaVenda.setData_hora(Timestamp.from(Instant.now()));
        novaVenda.setIds_produtos_vendidos(listinha);
        novaVenda.setUsuario(usuario);
        novaVenda.setValorTotal(valor_total.get());

        try{
            vendaRepository.save(novaVenda);
        }catch(Exception e){
            throw new RuntimeException("Não foi possível realizar venda");
        }

        for(int i = 0; i < listinha.size(); i++) {
            produtoService.decrementaQuantidade(produtosVendidosDTO.produtosVendidos().get(i).getQtd(), listinha.get(i));
        }

    }
}