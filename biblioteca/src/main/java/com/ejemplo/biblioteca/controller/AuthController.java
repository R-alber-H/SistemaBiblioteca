package com.ejemplo.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.biblioteca.dto.UsuarioCreateDTO;
import com.ejemplo.biblioteca.dto.UsuarioDTO;
import com.ejemplo.biblioteca.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
     private final UsuarioService usuarioService;

     @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@Valid @RequestBody UsuarioCreateDTO dto){
        return new ResponseEntity<>(usuarioService.crear(dto), HttpStatus.CREATED);
    }
}
