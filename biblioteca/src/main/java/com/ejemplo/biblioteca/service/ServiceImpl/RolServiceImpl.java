package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.dto.RolDTO;
import com.ejemplo.biblioteca.dto.RolRequestDTO;
import com.ejemplo.biblioteca.entity.Rol;
import com.ejemplo.biblioteca.exception.RolNoEncontradoException;
import com.ejemplo.biblioteca.repository.RolRepository;
import com.ejemplo.biblioteca.service.RolService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements RolService {

    private RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        super(rolRepository);
        this.rolRepository = rolRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<RolDTO> listarTodos() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream()
                .map(rol -> new RolDTO(
                        rol.getId(),
                        rol.getNombre(),
                        rol.getDescripcion(),
                        rol.getEstado()
                    ))
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RolDTO obtenerPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNoEncontradoException("El rol no encontrado"));
        return toDTO(rol);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    @Transactional
    public RolDTO crear(RolRequestDTO dto) {
        Rol rol = new Rol();
        rol.setNombre(dto.nombre());

        Rol rolGuardado = rolRepository.save(rol);

        return toDTO(rolGuardado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RolDTO actualizarDatos(Long id, RolRequestDTO dto) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNoEncontradoException("El rol no encontrado"));
        rol.setNombre(dto.nombre());
        Rol rolActualizado = rolRepository.save(rol);
        return toDTO(rolActualizado);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RolDTO cambiarEstado(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RolNoEncontradoException("El rol no encontrado"));
        rol.setEstado(rol.getEstado() == Estado.HABILITADO ? Estado.DESHABILITADO : Estado.HABILITADO);
        return toDTO(rol);    
    }

    private RolDTO toDTO(Rol rol) {
        return new RolDTO(
                rol.getId(),
                rol.getNombre(),
                rol.getDescripcion(),
                rol.getEstado());
    }

}
