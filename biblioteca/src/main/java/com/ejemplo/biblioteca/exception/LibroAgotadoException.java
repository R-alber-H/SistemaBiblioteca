package com.ejemplo.biblioteca.exception;

public class LibroAgotadoException extends RuntimeException{
    public LibroAgotadoException(String mensaje){
        super(mensaje);
    }
}
