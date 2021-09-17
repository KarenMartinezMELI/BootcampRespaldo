package com.spring.diploma.model;

public class AsignaturaDTO {

    private String nombre;
    private double nota;

    public AsignaturaDTO() {
    }

    public AsignaturaDTO(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "{" +
                "\"nombre\":\"" + nombre + '\"' +
                ", \"nota\":" + nota +
                '}';
    }
}
