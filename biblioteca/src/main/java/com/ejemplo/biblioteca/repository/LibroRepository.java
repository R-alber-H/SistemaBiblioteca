package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.Libro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
   Optional<Libro> findByIsbnIgnoreCase(String isbn);
}
