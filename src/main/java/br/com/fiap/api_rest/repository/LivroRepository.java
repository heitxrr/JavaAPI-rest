package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);
    List<Livro>findTop10ByPreco();
    List<Livro>findByTituloIs(String Autor);
    List<Livro>findByTituloEqualsIgnoreCase(String autor);
    List<Livro>findFirstByAutor(String autor);
    List<Livro>findDistinctByAutorNotIn(List<String> titulos);
    List<Livro>findByTituloIsNot(String titulo);
    List<Livro>findByIsbnIsNull();
    List<Livro>findByIsbnIsNotNull();
    List<Livro>findByEbookTrue();
    List<Livro>findByEbookFalse();
    List<Livro>findByTituloStartingWith(String prefix);
    List<Livro>findByEditoraEndingWith(String suffix);
    List<Livro>findByEditoraContaining(String infix);
    List<Livro>findByTituloLike(String likePattern);
    List<Livro>findByPrecoLessThan(double preco);
    List<Livro>findByPrecoLessThanEqual(double preco);
    List<Livro>findByPrecoGreaterThan(double preco);
    List<Livro>findByPrecoGreaterThanEqual(double preco);
    List<Livro>findByPrecoBetween(double min, double max);
    List<Livro>findByTituloIn(Collection<String> livrosBaratos);
    List<Livro>findByDataLancamentoBefore(Date dataLancamento);
    List<Livro>findByDataLancamentoAfter(Date dataLancamento);
    List<Livro>findByDataLancamentoBetween(Date dataLancamentoInicial, Date dataLancamentoFinal);
    List<Livro>findByTituloOrAutor(String titulo, String autor);
    List<Livro>findByTituloOrAutorAndEditora(String titulo, String autor, String editora);
    List<Livro>findByTituloOrderByTitulo(String titulo);
    List<Livro>findByTituloOrderByTituloAsc(String titulo);
    List<Livro>findByTituloOrderByTituloDesc(String titulo);
}
