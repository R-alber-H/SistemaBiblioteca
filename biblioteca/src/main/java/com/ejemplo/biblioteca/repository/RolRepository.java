package com.ejemplo.biblioteca.repository;

import com.ejemplo.biblioteca.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
