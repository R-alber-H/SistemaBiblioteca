package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.dto.RegistroPublicoDTO;
import com.ejemplo.biblioteca.dto.UsuarioCreateDTO;
import com.ejemplo.biblioteca.dto.UsuarioDTO;
import com.ejemplo.biblioteca.dto.UsuarioUpdateDTO;
import com.ejemplo.biblioteca.entity.Rol;
import com.ejemplo.biblioteca.entity.Usuario;
import com.ejemplo.biblioteca.exception.DniRegistradoException;
import com.ejemplo.biblioteca.exception.RolNoEncontradoException;
import com.ejemplo.biblioteca.exception.UsuarioNoEncontradoException;
import com.ejemplo.biblioteca.repository.RolRepository;
import com.ejemplo.biblioteca.repository.UsuarioRepository;
import com.ejemplo.biblioteca.service.UsuarioService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,RolRepository rolRepository,PasswordEncoder passwordEncoder) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public UsuarioDTO cambiarEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con id " + id+" no encontrado"));
        usuario.setEstado(usuario.getEstado() == Estado.HABILITADO ? Estado.DESHABILITADO : Estado.HABILITADO);
        Usuario actualizado = usuarioRepository.save(usuario);
        return toDTO(actualizado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    @Transactional
    public UsuarioDTO actualizarDatos(Long id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con id " + id+" no encontrado"));
        usuario.setNombres(dto.nombres());
        usuario.setApellidos(dto.apellidos());
        Usuario actualizado = usuarioRepository.save(usuario);
        return toDTO(actualizado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    @Transactional
    public UsuarioDTO crear(UsuarioCreateDTO dto) {
        Boolean existeDni = usuarioRepository.existsByDniIgnoreCaseAllIgnoreCase(dto.dni());
        if (existeDni) {
            throw new DniRegistradoException("El DNI ya esta registrado");
        }
        Rol rol = rolRepository.findById(dto.idRol())
            .orElseThrow(() -> new RolNoEncontradoException("Rol no encontrado"));
        Usuario usuario = new Usuario();
        usuario.setNombres(dto.nombres());
        usuario.setApellidos(dto.apellidos());
        usuario.setDni(dto.dni());
        usuario.setCorreo(dto.correo());
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        usuario.setRol(rol);

        Usuario usuarioCreado = usuarioRepository.save(usuario);

        return toDTO(usuarioCreado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new UsuarioDTO(
            usuario.getId(),
            usuario.getNombres(),
            usuario.getApellidos(),
            usuario.getDni(),
            usuario.getCorreo(),
            usuario.getEstado(),
            usuario.getRol()
        )).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public UsuarioDTO buscarPorId(Long id) {

       Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con id " + id+" no encontrado"));
        return toDTO(usuario);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getDni(),
                usuario.getCorreo(),
                usuario.getEstado(),
                usuario.getRol());
    }

    @Override
    @Transactional
    public UsuarioDTO registroPublico(RegistroPublicoDTO dto) {

    Long rolClienteId = rolRepository.findByNombre("CLIENTE")
            .orElseThrow(() -> new RuntimeException("Rol CLIENTE no encontrado"))
            .getId();

    UsuarioCreateDTO nuevo = new UsuarioCreateDTO(
            dto.nombres(),
            dto.apellidos(),
            dto.dni(),
            dto.correo(),
            dto.password(),
            rolClienteId
    );

    return crear(nuevo);
}
}
