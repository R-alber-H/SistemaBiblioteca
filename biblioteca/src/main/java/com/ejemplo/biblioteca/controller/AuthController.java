package com.ejemplo.biblioteca.controller;

import com.ejemplo.biblioteca.dto.*;
import com.ejemplo.biblioteca.service.CustomUserDetailsService;
import com.ejemplo.biblioteca.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.biblioteca.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AuthController {
    
     private final UsuarioService usuarioService;
     private final AuthenticationManager authenticationManager;
     private final JwtService jwtService;
     private final CustomUserDetailsService customUserDetailsService;

     @PostMapping("/registro")
     public ResponseEntity<UsuarioDTO> registrarUsuario( @RequestBody RegistroPublicoDTO dto){
        UsuarioDTO usuarioRegistrado = usuarioService.registroPublico(dto);
        return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
     }

    @PostMapping("/login")
    @Operation(security = {})
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.correo(), dto.password())
            );
            UserDetails user = customUserDetailsService.loadUserByUsername(dto.correo());
            String token = jwtService.generarToken(user);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(
                    new ApiError(401, "Credenciales incorrectas o token inválido")
            );
        }
    }
}