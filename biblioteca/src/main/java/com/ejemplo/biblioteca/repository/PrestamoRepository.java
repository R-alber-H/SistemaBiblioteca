package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
