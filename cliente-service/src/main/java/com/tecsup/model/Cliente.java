package com.tecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El nro_dni es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El nro_dni debe tener exactamente 8 digitos")
    @Column(name = "nro_dni", length = 8)
    private String nroDni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNroDni() {
        return nroDni;
    }

    public void setNroDni(String nroDni) {
        this.nroDni = nroDni;
    }
}
