package br.com.fiap.api_rest.repository;

import br.com.fiap.api_rest.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findById(Long id);

    List<Autor> findAllById(List<Long> ids);

}
