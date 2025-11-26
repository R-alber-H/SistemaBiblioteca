package com.ejemplo.biblioteca.service;

import java.util.List;

import com.ejemplo.biblioteca.dto.RolDTO;
import com.ejemplo.biblioteca.dto.RolRequestDTO;
import com.ejemplo.biblioteca.entity.Rol;

public interface RolService extends GenericService<Rol,Long> {
    RolDTO cambiarEstado(Long id);
    List<RolDTO> listarTodos();
    RolDTO obtenerPorId(Long id);
    RolDTO crear(RolRequestDTO dto);
    RolDTO actualizarDatos(Long id, RolRequestDTO dto);
}
