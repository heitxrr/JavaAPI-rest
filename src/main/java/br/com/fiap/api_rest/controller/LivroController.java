package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.LivroRequest;
import br.com.fiap.api_rest.dto.LivroResponseDTO;
import br.com.fiap.api_rest.model.Livro;
import br.com.fiap.api_rest.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Operation(summary = "Cria um novo livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Livro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @PostMapping
    public ResponseEntity<Livro> createLivro(@Valid @RequestBody LivroRequest livroRequest) {
        Livro livroSalvo = livroService.salvarLivro(livroRequest);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os livros por páginas")
    @GetMapping
    public ResponseEntity<Page<LivroResponseDTO>> readLivros(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 2);
        return new ResponseEntity<>(livroService.findAllDTO(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna um livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Nenhum livro encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> readLivro(@PathVariable Long id) {
        Optional<LivroResponseDTO> livro = livroService.findLivroById(id);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um livro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro atualizado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody LivroRequest livroRequest) {
        Optional<Livro> livroAtualizado = livroService.atualizarLivro(id, livroRequest);
        return livroAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Exclui um livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro excluído"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        if (livroService.excluirLivro(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
