package br.com.fiap.api_rest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Biblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Nome;
    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @OneToMany(mappedBy = "biblioteca")
    private List<Livro> livros;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
