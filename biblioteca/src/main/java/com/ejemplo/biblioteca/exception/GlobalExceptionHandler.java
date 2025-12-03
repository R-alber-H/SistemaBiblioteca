package com.ejemplo.biblioteca.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<Map<String,String>> handleUserNotFound(UsuarioNoEncontradoException ex) {
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(LibroNoEncontradoException.class)
public ResponseEntity<Map<String, String>> handleLibroNoEncontrado(LibroNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());
    return ResponseEntity.status(404).body(error);
}

@ExceptionHandler(RolNoEncontradoException.class)
public ResponseEntity<Map<String, String>> handleRolNoEncontrado(RolNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());

    return ResponseEntity.status(404).body(error);
}

@ExceptionHandler(PrestamoNoEncontradoException.class)
public ResponseEntity<Map<String, String>> handlePrestamoNoEncontrado(PrestamoNoEncontradoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());

    return ResponseEntity.status(404).body(error);
}

@ExceptionHandler(EstadoPrestamoInvalidoException.class)
public ResponseEntity<Map<String, String>> handleEstadoPrestamoInvalido(EstadoPrestamoInvalidoException ex) {
    Map<String, String> error = new HashMap<>();
    error.put("error", ex.getMessage());

    return ResponseEntity.status(404).body(error);
}

     @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv ->
                errors.put(cv.getPropertyPath().toString(), cv.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
}
