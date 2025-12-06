package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.Prestamo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioId(Long usuarioId);
}
