package com.ejemplo.biblioteca.dto;

import com.ejemplo.biblioteca.entity.Rol;
import com.ejemplo.biblioteca.utils.Estado;

public record UsuarioDTO(
    Long id,
    String nombres,
    String apellidos,
    String dni,
    String correo,
    Estado estado,
    Rol rol
) {  
}
