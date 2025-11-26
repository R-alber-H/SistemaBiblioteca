package com.ejemplo.biblioteca.dto;

import java.util.List;

import com.ejemplo.biblioteca.utils.EstadoPrestamo;

public record PrestamoDTO(
    Long id,
    Long usuarioId,
    EstadoPrestamo estado,
    List<DetallePrestamoDTO> detalles
) {
    
}
