package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Biblioteca;
import br.com.fiap.api_rest.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    // Buscar uma biblioteca por ID
    public Biblioteca findById(Long id) {
        return bibliotecaRepository.findById(id).orElse(null);
    }

    // Buscar várias bibliotecas por seus IDs
    public List<Biblioteca> findByIds(List<Long> ids) {
        return bibliotecaRepository.findAllById(ids);
    }

    // Salvar uma nova biblioteca
    public Biblioteca salvarBiblioteca(Biblioteca biblioteca) {
        return bibliotecaRepository.save(biblioteca);
    }

    // Atualizar uma biblioteca
    public Biblioteca atualizarBiblioteca(Long id, Biblioteca bibliotecaDetails) {
        return bibliotecaRepository.findById(id).map(biblioteca -> {
            biblioteca.setNome(bibliotecaDetails.getNome());
            return bibliotecaRepository.save(biblioteca);
        }).orElse(null);
    }

    // Excluir uma biblioteca
    public boolean excluirBiblioteca(Long id) {
        return bibliotecaRepository.findById(id).map(biblioteca -> {
            bibliotecaRepository.delete(biblioteca);
            return true;
        }).orElse(false);
    }
}
