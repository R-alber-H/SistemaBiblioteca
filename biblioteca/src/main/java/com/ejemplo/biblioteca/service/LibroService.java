package com.ejemplo.biblioteca.service;

import java.util.List;

import com.ejemplo.biblioteca.dto.LibroCreateDto;
import com.ejemplo.biblioteca.dto.LibroDTO;
import com.ejemplo.biblioteca.dto.LibroUpdateDTO;
import com.ejemplo.biblioteca.entity.Libro;

public interface LibroService extends GenericService<Libro,Long>{
    LibroDTO cambiarEstado(Long id);
    LibroDTO registrarLibro(LibroCreateDto dto);
    List<LibroDTO> listarLibros();
    LibroDTO buscarPorId(Long id);
    LibroDTO actualizarLibro(LibroUpdateDTO dto,Long id);
}
