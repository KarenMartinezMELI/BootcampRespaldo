package com.spring.diploma.model;

import java.util.List;

public class AlumnoDTO {

    private String nombre;
    private List<AsignaturaDTO> asignaturas;

    public AlumnoDTO() {
    }

    public AlumnoDTO(String nombre, List<AsignaturaDTO> asignaturas) {
        this.nombre = nombre;
        this.asignaturas = asignaturas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AsignaturaDTO> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<AsignaturaDTO> asignaturas) {
        this.asignaturas = asignaturas;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nombre\":\"" + nombre + '\"' +
                ", \"asignaturas\":" + asignaturas +
                '}';
    }
}
