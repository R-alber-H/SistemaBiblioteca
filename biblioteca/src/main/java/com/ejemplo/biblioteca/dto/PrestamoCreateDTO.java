package com.ejemplo.biblioteca.dto;

import java.util.List;

public record PrestamoCreateDTO(
    Long usuarioId,
    List<Long> idLlibros
) {
}
