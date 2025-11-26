package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
     boolean existsByDniIgnoreCaseAllIgnoreCase(String dni);
}
