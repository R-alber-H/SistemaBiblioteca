package com.ejemplo.biblioteca.controller;

import com.ejemplo.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id){
        return new ResponseEntity<>(usuarioService.obtenerPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        return new ResponseEntity<>(usuarioService.crearUsuario(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Long id,
                                                    @Valid @RequestBody UsuarioRequestDTO dto){
        return new ResponseEntity<>(usuarioService.editarUsuario(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deshabilitarUsuario(@PathVariable Long id){
        usuarioService.deshabilitarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
