package com.ejemplo.biblioteca.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.entity.Usuario;
import com.ejemplo.biblioteca.repository.UsuarioRepository;
import com.ejemplo.biblioteca.service.UsuarioService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @Override
    public Usuario cambiarEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEstado(usuario.getEstado()==Estado.HABILITADO ? Estado.DESHABILITADO : Estado.HABILITADO);
        return usuarioRepository.save(usuario);
    }

}
