package com.ejemplo.biblioteca.service;

import com.ejemplo.biblioteca.entity.Libro;

public interface LibroService extends GenericService<Libro,Long>{
    Libro cambiarEstado(Long id);
}
