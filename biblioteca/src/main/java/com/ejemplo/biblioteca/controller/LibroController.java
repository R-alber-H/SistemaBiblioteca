package com.ejemplo.biblioteca.controller;

import com.ejemplo.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService  libroService;

    @GetMapping
    public ResponseEntity<List<LibroDTO>> listarLibros(){
        return new ResponseEntity<>(libroService.listarLibros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> obtenerLibro(@PathVariable Long id){
        return new ResponseEntity<>(libroService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LibroDTO> crearLibro(@Valid @RequestBody LibroRequestDTO dto){
        return new ResponseEntity<>(libroService.crearLibro(),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> editarLibro(@PathVariable Long id,
                                                @Valid @RequestBody LibroRequestDTO dto){
        return new ResponseEntity<>(libroService.editarLibro(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitarLibro(@PathVariable Long id){
        libroService.deshabilitarLibro(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
