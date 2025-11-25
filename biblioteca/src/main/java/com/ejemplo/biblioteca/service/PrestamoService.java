package com.ejemplo.biblioteca.service;

import java.util.List;

import com.ejemplo.biblioteca.entity.Prestamo;

public interface PrestamoService extends GenericService<Prestamo,Long>{
    Prestamo crearPrestamo(Long usuarioId, List<Long> libroIds);
}
