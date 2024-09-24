package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.VendasDiaInterface;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        List<VendasDiaInterface> vendasDia = vendaRepository.findVendasPeriodoTempo(data, endData);
        List<VendasPorTempoDTO> vendasPorTempoDTOList = new ArrayList<>();

        vendasDia.forEach(venda -> {
            VendasPorTempoDTO vendasPorTempoDTO = new VendasPorTempoDTO(venda.getData_hora(), venda.getValor_total());
            vendasPorTempoDTOList.add(vendasPorTempoDTO);
        });
        return ResponseEntity.ok().body(vendasPorTempoDTOList);
    }

}
