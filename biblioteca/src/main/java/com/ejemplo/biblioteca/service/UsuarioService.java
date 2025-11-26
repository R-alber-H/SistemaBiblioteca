package com.ejemplo.biblioteca.service;

import java.util.List;

import com.ejemplo.biblioteca.dto.UsuarioCreateDTO;
import com.ejemplo.biblioteca.dto.UsuarioDTO;
import com.ejemplo.biblioteca.dto.UsuarioUpdateDTO;
import com.ejemplo.biblioteca.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario,Long>{
    UsuarioDTO cambiarEstado(Long id);
    UsuarioDTO actualizarDatos(Long id, UsuarioUpdateDTO dto);
    UsuarioDTO crear(UsuarioCreateDTO usuario);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO buscarPorId(Long id);
}
