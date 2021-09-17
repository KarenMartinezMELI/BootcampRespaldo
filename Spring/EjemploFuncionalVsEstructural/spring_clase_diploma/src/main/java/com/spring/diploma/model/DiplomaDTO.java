package com.spring.diploma.model;

public class DiplomaDTO {

    private String time;
    private String mensaje;
    private double promedio;
    private AlumnoDTO alumno;


    public DiplomaDTO() {
    }

    public DiplomaDTO(String time, String mensaje, double promedio, AlumnoDTO alumno) {
        this.time = time;
        this.mensaje = mensaje;
        this.promedio = promedio;
        this.alumno = alumno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }

    public AlumnoDTO getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoDTO alumno) {
        this.alumno = alumno;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
