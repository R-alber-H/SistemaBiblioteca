package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.entity.DetallePrestamo;
import com.ejemplo.biblioteca.entity.Libro;
import com.ejemplo.biblioteca.entity.Prestamo;
import com.ejemplo.biblioteca.entity.Usuario;
import com.ejemplo.biblioteca.repository.DetallePrestamoRepository;
import com.ejemplo.biblioteca.repository.LibroRepository;
import com.ejemplo.biblioteca.repository.PrestamoRepository;
import com.ejemplo.biblioteca.repository.UsuarioRepository;
import com.ejemplo.biblioteca.service.PrestamoService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class PrestamoServiceImpl extends GenericServiceImpl<Prestamo,Long> implements PrestamoService{

    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePrestamoRepository detallePrestamoRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,LibroRepository libroRepository,UsuarioRepository usuarioRepository,DetallePrestamoRepository detallePrestamoRepository) {
        super(prestamoRepository);
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
        this.detallePrestamoRepository = detallePrestamoRepository;
    }

    @Transactional
    @Override
    public Prestamo crearPrestamo(Long usuarioId, List<Long> libroIds) {

    Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    Prestamo prestamo = new Prestamo();
    prestamo.setUsuario(usuario);
    prestamo.setEstado(Estado.HABILITADO);

    Prestamo prestamoGuardado = prestamoRepository.save(prestamo);
    List<DetallePrestamo> detalles = new ArrayList<>();

    for (Long libroId : libroIds) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.descontarStock();
        libroRepository.save(libro);
    
        DetallePrestamo detalle = new DetallePrestamo();
        detalle.setPrestamo(prestamoGuardado);
        detalle.setLibro(libro);
        detalles.add(detalle);
    }
    detallePrestamoRepository.saveAll(detalles);
    return prestamoGuardado;
}

    
}
