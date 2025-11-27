package com.ejemplo.biblioteca.controller;

import com.ejemplo.biblioteca.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
    private final RolService rolService;

    @GetMapping
    public ResponseEntity<List<RolDTO>> listarRoles(){
        return new ResponseEntity<>(rolService.listarRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> buscarRolPorId(@PathVariable Long id){
        return new ResponseEntity<>(rolService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RolDTO> crearRol(@Valid @RequestBody RolRequestDTO dto){
        return new ResponseEntity<>(rolService.crearRol(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> editarRol(@PathVariable Long id, @Valid @RequestBody RolRequestDTO dto){
        return new ResponseEntity<>(rolService.editarRol(id), HttpStatus.OK);
    }
}
