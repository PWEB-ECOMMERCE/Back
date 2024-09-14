package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.usuario.Usuario;
import com.example.ecommerce.domain.venda.ComprasInterface;
import com.example.ecommerce.domain.venda.Venda;
import com.example.ecommerce.dto.produto.ProdutoResponseDTO;
import com.example.ecommerce.dto.relatorio.ComprasClientes;
import com.example.ecommerce.dto.relatorio.RelatorioComprasResponseDTO;
import com.example.ecommerce.repository.ProdutoRepository;
import com.example.ecommerce.repository.VendaRepository;
import com.example.ecommerce.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("relatorio")
public class RelatorioController {
    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioService usuarioService;

    @GetMapping("/compras")
    public ResponseEntity<List<RelatorioComprasResponseDTO>> comprasClientes(){

        List<ComprasInterface> vendas = vendaRepository.finda().stream().toList();
        List<RelatorioComprasResponseDTO> relatorioComprasResponseDTOS = new ArrayList<>();

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
}
