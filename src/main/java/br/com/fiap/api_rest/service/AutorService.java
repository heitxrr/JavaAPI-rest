package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.model.Autor;
import br.com.fiap.api_rest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Método para buscar um autor por ID, retornando um Optional
    public Autor findById(Long id) {
        Optional<Autor> autorOpt = autorRepository.findById(id);
        return autorOpt.orElseThrow(() -> new RuntimeException("Autor não encontrado"));
    }

    // Método para buscar múltiplos autores por IDs
    public List<Autor> findByIds(List<Long> ids) {
        return autorRepository.findAllById(ids);
    }

    // Método para salvar um autor
    public Autor salvarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Método para atualizar um autor
    public Autor atualizarAutor(Long id, Autor autorDetails) {
        return autorRepository.findById(id).map(autor -> {
            autor.setNome(autorDetails.getNome());
            return autorRepository.save(autor);
        }).orElseThrow(() -> new RuntimeException("Autor não encontrado para atualização"));
    }

    // Método para excluir um autor
    public boolean excluirAutor(Long id) {
        return autorRepository.findById(id).map(autor -> {
            autorRepository.delete(autor);
            return true;
        }).orElseThrow(() -> new RuntimeException("Autor não encontrado para exclusão"));
    }
}
