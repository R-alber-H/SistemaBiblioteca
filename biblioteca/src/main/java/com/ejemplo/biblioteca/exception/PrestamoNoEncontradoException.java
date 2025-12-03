package com.ejemplo.biblioteca.exception;

public class PrestamoNoEncontradoException extends RuntimeException{

    public PrestamoNoEncontradoException(String mensaje){
        super(mensaje);
    }
    
}
