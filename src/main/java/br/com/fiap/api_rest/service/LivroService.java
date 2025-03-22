package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.LivroRequest;
import br.com.fiap.api_rest.dto.LivroResponseDTO;
import br.com.fiap.api_rest.model.Livro;
import br.com.fiap.api_rest.model.Categoria;
import br.com.fiap.api_rest.model.Autor;
import br.com.fiap.api_rest.model.Biblioteca;
import br.com.fiap.api_rest.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private BibliotecaService bibliotecaService;

    public Livro salvarLivro(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.setTitulo(livroRequest.getTitulo());
        livro.setPreco(livroRequest.getPreco());
        livro.setIsbn(livroRequest.getIsbn());

        // Buscando as categorias, autores e bibliotecas pelos IDs
        List<Categoria> categorias = categoriaService.findByIds(livroRequest.getCategoriaIds());
        livro.setCategorias(categorias);

        List<Autor> autores = autorService.findByIds(livroRequest.getAutoresIds());
        livro.setAutores(autores);

        List<Biblioteca> bibliotecas = bibliotecaService.findByIds(livroRequest.getBibliotecaIds());
        livro.setBibliotecas(bibliotecas);

        return livroRepository.save(livro);
    }

    public Optional<LivroResponseDTO> findLivroById(Long id) {
        return livroRepository.findById(id)
                .map(livro -> new LivroResponseDTO(
                        livro.getId(),
                        livro.getTitulo(),
                        "/livros/" + livro.getId() // Adicionando o link esperado no DTO
                ));
    }

    public Optional<Livro> atualizarLivro(Long id, LivroRequest livroRequest) {
        return livroRepository.findById(id).map(livro -> {
            livro.setTitulo(livroRequest.getTitulo());
            livro.setPreco(livroRequest.getPreco());
            livro.setIsbn(livroRequest.getIsbn());

            // Buscando e atualizando as categorias, autores e bibliotecas
            List<Categoria> categorias = categoriaService.findByIds(livroRequest.getCategoriaIds());
            livro.setCategorias(categorias);

            List<Autor> autores = autorService.findByIds(livroRequest.getAutoresIds());
            livro.setAutores(autores);

            List<Biblioteca> bibliotecas = bibliotecaService.findByIds(livroRequest.getBibliotecaIds());
            livro.setBibliotecas(bibliotecas);

            return livroRepository.save(livro);
        });
    }

    public boolean excluirLivro(Long id) {
        return livroRepository.findById(id).map(livro -> {
            livroRepository.delete(livro);
            return true;
        }).orElse(false);
    }

    public Page<LivroResponseDTO> findAllDTO(Pageable pageable) {
        return livroRepository.findAll(pageable).map(livro -> new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                "/livros/" + livro.getId() // Adicionando o link esperado no DTO
        ));
    }
}
