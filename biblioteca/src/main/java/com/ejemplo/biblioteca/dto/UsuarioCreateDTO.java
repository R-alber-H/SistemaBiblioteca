package com.ejemplo.biblioteca.dto;

public record UsuarioCreateDTO(
    String nombres,
    String apellidos,
    String dni,
    String correo,
     String password,
    Long idRol
) {
    
}
