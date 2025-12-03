package com.ejemplo.biblioteca.exception;

public class EstadoPrestamoInvalidoException extends RuntimeException{
    public EstadoPrestamoInvalidoException(String mensaje){
        super(mensaje);
    }
}
