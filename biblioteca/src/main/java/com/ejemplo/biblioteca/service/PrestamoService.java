package com.ejemplo.biblioteca.service;

import java.util.List;

import com.ejemplo.biblioteca.dto.PrestamoDTO;
import com.ejemplo.biblioteca.entity.Prestamo;

public interface PrestamoService extends GenericService<Prestamo,Long>{
    PrestamoDTO crearPrestamo(Long usuarioId, List<Long> libroIds);
    List<PrestamoDTO> listarPrestamos();
    PrestamoDTO obtenerPrestamoPorId(Long id);
    PrestamoDTO cambiarEstadoPrestamo(Long id);
    List<PrestamoDTO> listarPrestamosDelUsuario();

}
