package com.ejemplo.biblioteca.entity;

import com.ejemplo.biblioteca.utils.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends BaseEntity{

    @Column(length = 100,nullable = false)
    @NotBlank(message = "Los nombres no puede estar vacio")
    private String nombres;

    @Column(length = 100,nullable = false)
    @NotBlank(message = "Los apellidos no puede estar vacio")
    private String apellidos;

    @Column(length = 8,nullable = false,unique = true)
    @NotBlank(message = "El DNI no puede estar vacio")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    private String dni;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado= Estado.HABILITADO;

    @ManyToOne(optional = false)
     @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
    
}
