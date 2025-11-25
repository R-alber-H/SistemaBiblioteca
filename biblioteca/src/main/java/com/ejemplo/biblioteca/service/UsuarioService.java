package com.ejemplo.biblioteca.service;

import com.ejemplo.biblioteca.entity.Usuario;

public interface UsuarioService extends GenericService<Usuario,Long>{
    Usuario cambiarEstado(Long id);
}
