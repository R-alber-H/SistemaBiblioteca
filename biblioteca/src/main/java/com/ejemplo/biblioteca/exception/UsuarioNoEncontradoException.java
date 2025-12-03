package com.ejemplo.biblioteca.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
