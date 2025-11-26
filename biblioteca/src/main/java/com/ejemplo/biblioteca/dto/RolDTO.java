package com.ejemplo.biblioteca.dto;

import com.ejemplo.biblioteca.utils.Estado;

public record RolDTO(
    Long id,
    String nombre,
    String descripcion,
    Estado estado
) {
    
}
