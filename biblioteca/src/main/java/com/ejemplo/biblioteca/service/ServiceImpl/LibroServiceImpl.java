package com.ejemplo.biblioteca.service.ServiceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.dto.LibroCreateDto;
import com.ejemplo.biblioteca.dto.LibroDTO;
import com.ejemplo.biblioteca.dto.LibroUpdateDTO;
import com.ejemplo.biblioteca.entity.Libro;
import com.ejemplo.biblioteca.exception.LibroNoEncontradoException;
import com.ejemplo.biblioteca.repository.LibroRepository;
import com.ejemplo.biblioteca.service.LibroService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class LibroServiceImpl extends GenericServiceImpl<Libro, Long> implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
        this.libroRepository = libroRepository;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    @Transactional
    @Override
    public LibroDTO cambiarEstado(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado"));
        libro.setEstado(libro.getEstado() == Estado.HABILITADO ? Estado.DESHABILITADO : Estado.HABILITADO);
        Libro libroActualizado = libroRepository.save(libro);
        return toDTO(libroActualizado);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    @Override
    public LibroDTO registrarLibro(LibroCreateDto dto) {
        Optional<Libro> libroExistente = libroRepository.findByIsbnIgnoreCase(dto.isbn());

        if (libroExistente.isPresent()) {
            Libro libro = libroExistente.get();
            libro.setStock(libro.getStock() + dto.stock());
            return toDTO(libroRepository.save(libro));
        }
        Libro nuevo = new Libro();
        nuevo.setTitulo(dto.titulo());
        nuevo.setAutor(dto.autor());
        nuevo.setIsbn(dto.isbn());
        nuevo.setStock(dto.stock());

        return toDTO(libroRepository.save(nuevo));
    }

    @Override
    public List<LibroDTO> listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream().map(libro -> new LibroDTO(
            libro.getId(),
            libro.getAutor(),
            libro.getTitulo(),
            libro.getEstado(),
            libro.getStock(),
            libro.getIsbn()
        )).toList();
    }

    @Override
    public LibroDTO buscarPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado"));;
        return toDTO(libro);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BIBLIOTECARIO')")
    @Override
    public LibroDTO actualizarLibro(LibroUpdateDTO dto, Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado"));
        libro.setAutor(dto.autor());
        libro.setTitulo(dto.titulo());
        Libro actualizado = libroRepository.save(libro);
        return toDTO(actualizado);
    }

    private LibroDTO toDTO(Libro libro) {
        return new LibroDTO(
                libro.getId(),
                libro.getAutor(),
                libro.getTitulo(),
                libro.getEstado(),
                libro.getStock(),
                libro.getIsbn());
    }

}
