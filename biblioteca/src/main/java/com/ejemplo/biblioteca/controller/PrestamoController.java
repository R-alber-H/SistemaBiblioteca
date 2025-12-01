package com.ejemplo.biblioteca.controller;

import com.ejemplo.biblioteca.dto.PrestamoCreateDTO;
import com.ejemplo.biblioteca.dto.PrestamoDTO;
import com.ejemplo.biblioteca.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<PrestamoDTO>> listarPrestamos(){
        return new ResponseEntity<>(prestamoService.listarPrestamos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO> buscarPrestamoPorId(@PathVariable Long id){
        return new ResponseEntity<>(prestamoService.obtenerPrestamoPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrestamoDTO> crearPrestamo(@Valid @RequestBody PrestamoCreateDTO dto){
        return new ResponseEntity<>(prestamoService.crearPrestamo(dto.usuarioId(),dto.idLlibros()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestamoDTO> cambiarEstadoPrestamo(@PathVariable Long id){
        return new ResponseEntity<>(prestamoService.cambiarEstadoPrestamo(id), HttpStatus.OK);
    }
}
