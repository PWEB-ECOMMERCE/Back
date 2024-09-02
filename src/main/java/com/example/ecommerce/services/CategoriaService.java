package com.example.ecommerce.services;

import com.example.ecommerce.domain.categoria.Categoria;
import com.example.ecommerce.domain.produto.Produto;
import com.example.ecommerce.dto.categoria.CategoriaRequestDTO;
import com.example.ecommerce.dto.categoria.CreateCategoriaRequestDTO;
import com.example.ecommerce.dto.categoria.CategoriaResponseDTO;
import com.example.ecommerce.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaResponseDTO criarCategoriaResponseDTO(Categoria categoria) {

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getDescricao()
        );

        return categoriaResponseDTO;
    }

    public CategoriaResponseDTO retornaCategoria(int id) {
        Categoria categoria = categoriaRepository.findById(id);
        if ( categoria != null){
            return criarCategoriaResponseDTO(categoria);
        }else{
            throw new RuntimeException("Categoria não existe");
        }
    }

    public CategoriaResponseDTO retornaCategoriaPorDescricao(String descricao) {
        Categoria categoria = categoriaRepository.findByDescricao(descricao);
        if ( categoria != null){
            return criarCategoriaResponseDTO(categoria);
        }else{
            throw new RuntimeException("Categoria não existe");
        }
    }

    public Categoria createCategoria(CreateCategoriaRequestDTO createCategoriaRequestDTO) {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setDescricao(createCategoriaRequestDTO.descricao());
        //criarCategoriaResponseDTO(novaCategoria);
        Categoria categoria = this.categoriaRepository.save(novaCategoria);
        System.out.println(categoria.getId() + categoria.getDescricao());
        return categoria;
    }

    public CategoriaResponseDTO deletaCategoria(int id) {
        Categoria categoria = categoriaRepository.findById(id);
        categoriaRepository.deleteById(id);
        return criarCategoriaResponseDTO(categoria);
    }

    public CategoriaResponseDTO atualizarCategoria(int id, CategoriaRequestDTO edited) {
        Categoria existingCategoria = this.categoriaRepository.findById(id);
        if(existingCategoria == null) throw  new RuntimeException("Categoria não encontrado");

        if (edited.descricao() != null) {
            existingCategoria.setDescricao(edited.descricao());
        }
        Categoria categoria = this.categoriaRepository.save(existingCategoria);

        return criarCategoriaResponseDTO(categoria);
    }
}
