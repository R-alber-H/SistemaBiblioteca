package com.ejemplo.biblioteca.dto;

import com.ejemplo.biblioteca.utils.Estado;

public record LibroDTO(
    Long id,
    String autor,
    String titulo,
    Estado estado,
    int stock,
    String isbn
) {
}
