package br.com.fiap.api_rest.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class LivroRequest {

    @NotBlank(message = "O título não pode ser nulo ou vazio")
    @Size(min = 3, max = 254, message = "O título deve ter entre 3 e 254 caracteres")
    private String titulo;

    @NotNull(message = "A lista de autores é obrigatória")
    private List<Long> autoresIds;  // Lista de IDs de autores

    @Min(value = 1, message = "O preço deve ser no mínimo 1")
    @Max(value = 99, message = "O preço deve ser no máximo 99")
    private int preco;

    @NotNull(message = "A lista de categorias é obrigatória")
    private List<Long> categoriaIds;  // Lista de IDs de categorias

    @Pattern(regexp = "^970\\d{10}$|^970\\d{7}$", message = "O ISBN deve ter 10 OU 13 dígitos e iniciar por 970")
    private String isbn;

    @NotNull(message = "A lista de bibliotecas é obrigatória")
    private List<Long> bibliotecaIds;  // Lista de IDs de bibliotecas

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Long> getAutoresIds() {
        return autoresIds;
    }

    public void setAutoresIds(List<Long> autoresIds) {
        this.autoresIds = autoresIds;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public List<Long> getCategoriaIds() {
        return categoriaIds;
    }

    public void setCategoriaIds(List<Long> categoriaIds) {
        this.categoriaIds = categoriaIds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Long> getBibliotecaIds() {
        return bibliotecaIds;
    }

    public void setBibliotecaIds(List<Long> bibliotecaIds) {
        this.bibliotecaIds = bibliotecaIds;
    }
}
