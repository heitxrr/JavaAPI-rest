package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
