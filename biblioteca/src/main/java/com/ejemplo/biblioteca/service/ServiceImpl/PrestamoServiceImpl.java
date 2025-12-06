package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.dto.DetallePrestamoDTO;
import com.ejemplo.biblioteca.dto.PrestamoDTO;
import com.ejemplo.biblioteca.entity.DetallePrestamo;
import com.ejemplo.biblioteca.entity.Libro;
import com.ejemplo.biblioteca.entity.Prestamo;
import com.ejemplo.biblioteca.entity.Usuario;
import com.ejemplo.biblioteca.exception.EstadoPrestamoInvalidoException;
import com.ejemplo.biblioteca.exception.LibroNoEncontradoException;
import com.ejemplo.biblioteca.exception.PrestamoNoEncontradoException;
import com.ejemplo.biblioteca.exception.UsuarioNoEncontradoException;
import com.ejemplo.biblioteca.repository.DetallePrestamoRepository;
import com.ejemplo.biblioteca.repository.LibroRepository;
import com.ejemplo.biblioteca.repository.PrestamoRepository;
import com.ejemplo.biblioteca.repository.UsuarioRepository;
import com.ejemplo.biblioteca.service.PrestamoService;
import com.ejemplo.biblioteca.utils.EstadoPrestamo;

import jakarta.transaction.Transactional;

@Service
public class PrestamoServiceImpl extends GenericServiceImpl<Prestamo, Long> implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePrestamoRepository detallePrestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, LibroRepository libroRepository,
            UsuarioRepository usuarioRepository, DetallePrestamoRepository detallePrestamoRepository) {
        super(prestamoRepository);
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
        this.detallePrestamoRepository = detallePrestamoRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','CLIENTE')")
    @Transactional
    @Override
    public PrestamoDTO crearPrestamo(Long usuarioId, List<Long> libroIds) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con id " + usuarioId+" no encontrado"));

        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
        List<DetallePrestamo> detalles = new ArrayList<>();

        for (Long libroId : libroIds) {
            Libro libro = libroRepository.findById(libroId)
                    .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado"));

            libro.descontarStock();
            libroRepository.save(libro);

            DetallePrestamo detalle = new DetallePrestamo();
            detalle.setPrestamo(prestamoGuardado);
            detalle.setLibro(libro);
            detalles.add(detalle);
        }
        detallePrestamoRepository.saveAll(detalles);

        List<DetallePrestamoDTO> detallesDTO = detalles.stream()
                .map(d -> new DetallePrestamoDTO(
                        d.getId(),
                        d.getLibro().getId(),
                        d.getLibro().getTitulo()))
                .toList();

        return toDTO(prestamoGuardado, detallesDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    @Override
    public List<PrestamoDTO> listarPrestamos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();
        return prestamos.stream()
                .map(prestamo -> {
                    List<DetallePrestamoDTO> detallesDTO = prestamo.getDetalles().stream()
                            .map(d -> new DetallePrestamoDTO(
                                    d.getId(),
                                    d.getLibro().getId(),
                                    d.getLibro().getTitulo()))
                            .toList();

                    return toDTO(prestamo, detallesDTO);
                })
                .toList();
    }

    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','CLIENTE')")
    @Override
    public PrestamoDTO obtenerPrestamoPorId(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException("Prestamo no encontrado"));
        List<DetallePrestamoDTO> detallesDTO = prestamo.getDetalles().stream()
                .map(d -> new DetallePrestamoDTO(
                        d.getId(),
                        d.getLibro().getId(),
                        d.getLibro().getTitulo()))
                .toList();
        return toDTO(prestamo, detallesDTO);

    }

    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    @Override
    public PrestamoDTO cambiarEstadoPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException("Prestamo no encontrado"));

        switch (prestamo.getEstado()) {
            case PRESTADO -> prestamo.setEstado(EstadoPrestamo.DEVUELTO);
            case DEVUELTO -> throw new EstadoPrestamoInvalidoException("El préstamo ya fue devuelto");
        }
        
        prestamoRepository.save(prestamo);

        List<DetallePrestamoDTO> detallesDTO = prestamo.getDetalles().stream()
                .map(d -> new DetallePrestamoDTO(
                        d.getId(),
                        d.getLibro().getId(),
                        d.getLibro().getTitulo()))
                .toList();
        return toDTO(prestamo, detallesDTO);
    }

    private PrestamoDTO toDTO(Prestamo prestamo, List<DetallePrestamoDTO> detallesDTO) {
        return new PrestamoDTO(
                prestamo.getId(),
                prestamo.getUsuario().getId(),
                prestamo.getEstado(),
                detallesDTO);
    }

    
@PreAuthorize("hasRole('CLIENTE')")
@Override
public List<PrestamoDTO> listarPrestamosDelUsuario() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String correo = auth.getName();

    Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

    List<Prestamo> prestamos = prestamoRepository.findByUsuarioId(usuario.getId());

    return prestamos.stream()
            .map(prestamo -> {
                List<DetallePrestamoDTO> detallesDTO = prestamo.getDetalles().stream()
                        .map(d -> new DetallePrestamoDTO(
                                d.getId(),
                                d.getLibro().getId(),
                                d.getLibro().getTitulo()))
                        .toList();
                return toDTO(prestamo, detallesDTO);
            })
            .toList();
}
}
