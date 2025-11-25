package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePrestamoRepository extends JpaRepository<DetallePrestamo, Long> {
}
