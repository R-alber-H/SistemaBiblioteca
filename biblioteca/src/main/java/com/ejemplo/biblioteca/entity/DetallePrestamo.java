package com.ejemplo.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DetallePrestamo extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_prestamo", nullable = false)
    private Prestamo prestamo;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;
}
