package br.com.fiap.api_rest.dto;

import java.util.List;

public record LivroRequestDTO(String titulo,
                              List<Long> autorId) {
}


