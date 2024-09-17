package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.domain.venda.VendasDiaInterface;
import com.example.ecommerce.domain.venda_produto.VendaProduto;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.dto.relatorio.RelatorioComprasResponseDTO;
import com.example.ecommerce.dto.relatorio.VendasPorTempoDTO;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.repository.VendaProdutoRepository;
import com.example.ecommerce.repository.VendaRepository;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("relatorio")
public class RelatorioController {
    private final VendaRepository vendaRepository;
    private final VendaProdutoRepository vendaProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioService usuarioService;

    @GetMapping("/compras")
    public ResponseEntity<List<RelatorioComprasResponseDTO>> comprasClientes(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                                                             @RequestParam("endData") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endData){


        List<RelatorioComprasResponseDTO> relatorioComprasResponseDTOS = new ArrayList<>();
        List<ComprasInterface> vendas = vendaRepository.finda(data, endData).stream().toList();

        for(int i = 0; i < vendas.size(); i++) {
            Usuario usuario = usuarioService.getUsuarioByID(vendas.get(i).getUsuario_id());
            RelatorioComprasResponseDTO relatorioComprasResponseDTO = new RelatorioComprasResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    Integer.parseInt(vendas.get(i).getNum()));
            relatorioComprasResponseDTOS.add(relatorioComprasResponseDTO);
        }

        return ResponseEntity.ok().body(relatorioComprasResponseDTOS);
    }



    @GetMapping("/produtosEsgotados")
    public ResponseEntity<List<ProdutoResponseDTO>> retornaProdutosEsgotados(){
        List<ProdutoResponseDTO> produtosEsgotados = produtoRepository.produtosEsgotados().stream().map(ProdutoResponseDTO::new).toList();

        return ResponseEntity.ok().body(produtosEsgotados);
    }

    @GetMapping("/totalValorDia")
    public ResponseEntity<List<VendasPorTempoDTO>> totalValorPorDia(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
                                                                    @RequestParam("endData") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endData) {


        List<VendaProduto> vendaProdutoList = vendaProdutoRepository.findAll();

        List<AtomicInteger> valoresTotals = new ArrayList<>();
        AtomicInteger valorTotal = new AtomicInteger();

        //LocalDate data = LocalDate.parse("2024-09-11");
        //LocalDate endData = LocalDate.parse("2024-09-14");


        List<String> vendas = vendaRepository.findByData_hora(data);
        List<VendasDiaInterface> vendasDia = vendaRepository.findVendasPeriodoTempo(data, endData);
        List<VendasPorTempoDTO> vendasPorTempoDTOList = new ArrayList<>();

        vendasDia.forEach(venda -> {
            Produto produto = produtoRepository.findById(venda.getProduto_id());
            System.out.println("compra: " + venda.getId() + "com " + venda.getQuantidade() + " produtos por: R$ " + produto.getPreco() + " total: " + venda.getQuantidade() * produto.getPreco());
            valorTotal.addAndGet(produto.getPreco() * venda.getQuantidade());
        });

        for ( int i = 1; i < vendasDia.size(); i++) {
            Produto produto = produtoRepository.findById(vendasDia.get(i).getProduto_id());
            valorTotal.set(0);
            valorTotal.getAndAdd(vendasDia.get(i).getQuantidade() * produto.getPreco());
            int valor = vendasDia.get(i).getQuantidade() * produto.getPreco();
            //valoresTotals.add(valorTotal);
            System.out.println(valorTotal);
            VendasPorTempoDTO vendasPorTempoDTO = new VendasPorTempoDTO(vendasDia.get(i).getData_hora(), valor);
            vendasPorTempoDTOList.add(vendasPorTempoDTO);

        }

        return ResponseEntity.ok().body(vendasPorTempoDTOList);
    }
}
