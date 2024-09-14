package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.domain.venda_produto.VendaProduto;
import com.example.ecommerce.dto.Venda.VendaRequestDTO;
import com.example.ecommerce.dto.Venda.VendaResponseDTO;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.repository.UsuarioRepository;
import com.example.ecommerce.repository.VendaProdutoRepository;
import com.example.ecommerce.repository.VendaRepository;
import com.example.ecommerce.services.ProdutoService;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/venda")
public class VendaController {
    private final VendaRepository vendaRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VendaProdutoRepository vendaProdutoRepository;

    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> getVendas(){
        List<VendaResponseDTO> vendas = vendaRepository.findAll().stream().map(VendaResponseDTO::new).toList();
        return ResponseEntity.ok().body(vendas);
    }

    @GetMapping("/esp/{idCliente}")
    public ResponseEntity<List<VendaResponseDTO>> getComprasCliente(@PathVariable String idCliente) {
        List<VendaResponseDTO> vendas = vendaRepository.findAllByUsuarioId(idCliente).stream().map(VendaResponseDTO::new).toList();
        return ResponseEntity.ok().body(vendas);
    }

    @PostMapping
    public void novaVenda(@RequestBody VendaRequestDTO vendaRequestDTO) {
        Usuario usuario = usuarioService.getUsuarioByID(vendaRequestDTO.userId());
        Venda novaVenda = new Venda();
        novaVenda.setData_hora(LocalDate.now());
        novaVenda.setUsuario(usuario);

        Venda venda = vendaRepository.save(novaVenda);

        for (int i = 0; i < vendaRequestDTO.itensCarrinho().size(); i++) {
            ProdutoResponseDTO produto = produtoService.retornaProduto(vendaRequestDTO.itensCarrinho().get(i).id());
            Produto novoProduto = new Produto();
            novoProduto.setId(produto.id());
            novoProduto.setQuantidade(produto.quantidade());
            novoProduto.setDescricao(produto.descricao());
            novoProduto.setImagemUrl(produto.foto());
            novoProduto.setPreco(produto.preco());
            novoProduto.setCategoria(produto.categoria());
            novoProduto.setNome(produto.nome());

            VendaProduto vendaProduto = new VendaProduto();
            vendaProduto.setVenda(venda);
            vendaProduto.setProduto(novoProduto);
            vendaProduto.setQuantidade(vendaRequestDTO.itensCarrinho().get(i).qtd());

            vendaProdutoRepository.save(vendaProduto);

            produtoService.decrementaQuantidade(vendaProduto.getQuantidade(), novoProduto.getId());
        }
    }

    @DeleteMapping("/{idVenda}")
    public ResponseEntity deletarVenda(@PathVariable int idVenda){

        vendaRepository.deleteById(idVenda);
        return ResponseEntity.ok().build();
    }
}
