package com.ejemplo.biblioteca.entity;

import com.ejemplo.biblioteca.utils.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Libro extends BaseEntity {

    @NotBlank(message = "El nombre del autor no puede estar vacío")
    private String autor;

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.HABILITADO;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private int stock;

}
