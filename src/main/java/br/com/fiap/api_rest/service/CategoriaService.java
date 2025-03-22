package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Categoria;
import br.com.fiap.api_rest.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para buscar uma categoria por ID
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    // Método para buscar todas as categorias
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    // Método para buscar categorias por uma lista de IDs
    public List<Categoria> findByIds(List<Long> ids) {
        return categoriaRepository.findAllById(ids);
    }

    // Método para salvar uma nova categoria
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para atualizar uma categoria existente
    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    // Método para excluir uma categoria
    public boolean excluirCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
        return true;
    }
}
