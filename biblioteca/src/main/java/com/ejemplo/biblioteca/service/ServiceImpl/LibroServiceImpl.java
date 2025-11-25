package com.ejemplo.biblioteca.service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.ejemplo.biblioteca.entity.Libro;
import com.ejemplo.biblioteca.repository.LibroRepository;
import com.ejemplo.biblioteca.service.LibroService;
import com.ejemplo.biblioteca.utils.Estado;

import jakarta.transaction.Transactional;

@Service
public class LibroServiceImpl extends GenericServiceImpl<Libro,Long> implements LibroService{

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        super(libroRepository);
        this.libroRepository = libroRepository;
    }

    @Transactional
    @Override
    public Libro cambiarEstado(Long id) {
        Libro libro = libroRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Libro no encontrado"));
        libro.setEstado(libro.getEstado()==Estado.HABILITADO ? Estado.DESHABILITADO : Estado.HABILITADO);
        return libroRepository.save(libro);
    }
    
}
