package com.ejemplo.biblioteca.dto;

public record LibroCreateDto(
    String autor,
    String titulo,
    int stock,
    String isbn
) {  
}
