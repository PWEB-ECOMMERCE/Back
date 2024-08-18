package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.categoria.Categoria;
import com.example.ecommerce.dto.categoria.CategoriaRequestDTO;
import com.example.ecommerce.dto.categoria.CreateCategoriaRequestDTO;
import com.example.ecommerce.dto.categoria.CategoriaResponseDTO;
import com.example.ecommerce.repository.CategoriaRepository;
import com.example.ecommerce.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("categoria")
public class CategoriaController {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll(){
        List<CategoriaResponseDTO> categoriaList = categoriaRepository.findAll().stream().map(CategoriaResponseDTO::new).toList();
        return ResponseEntity.ok().body(categoriaList);
    }

    @GetMapping("/esp/{categoriaID}")
    public ResponseEntity<CategoriaResponseDTO> getCategoria(@PathVariable int produtoID) {
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.retornaCategoria(produtoID);
        return ResponseEntity.ok().body(categoriaResponseDTO);
    }

    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody CreateCategoriaRequestDTO createCategoriaRequestDTO, UriComponentsBuilder uriComponentsBuilder){
        //CategoriaResponseDTO categoriaResponseDTO = this.categoriaService.createCategoria(createCategoriaRequestDTO);
        Categoria categoria = categoriaService.createCategoria(createCategoriaRequestDTO);
        URI produtoURI = uriComponentsBuilder.path("categoria/esp/{produtoID}").buildAndExpand(categoria).toUri();

        return ResponseEntity.created(produtoURI).body(categoria);
    }

    @DeleteMapping("/esp/{categoriaID}")
    public ResponseEntity<CategoriaResponseDTO> deleteCategoria(@PathVariable int categoriaID) {
        return ResponseEntity.ok().body(this.categoriaService.deletaCategoria(categoriaID));
    }

    @PatchMapping("/esp/{categoriaID}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable int categoriaID, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        return ResponseEntity.ok().body(categoriaService.atualizarCategoria(categoriaID, categoriaRequestDTO));
    }

}
