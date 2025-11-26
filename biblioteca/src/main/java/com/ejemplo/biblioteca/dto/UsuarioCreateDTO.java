package com.ejemplo.biblioteca.dto;

import com.ejemplo.biblioteca.entity.Rol;

public record UsuarioCreateDTO(
    String nombres,
    String apellidos,
    String dni,
    String correo,
    Rol rol
) {
    
}
