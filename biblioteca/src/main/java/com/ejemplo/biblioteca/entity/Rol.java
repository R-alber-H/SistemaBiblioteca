package com.ejemplo.biblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Rol extends BaseEntity {

    @Column(length = 100,nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    
}
