package dom.ej.clase23.dto;

public class DiplomaDTO extends RespuestaGenericaDTO {
    String mensaje;
    double promedio;
    StudentDTO student;

    public DiplomaDTO(StudentDTO std,String msg, double prom){
        this.student=std;
        this.mensaje=msg;
        this.promedio=prom;
    }

    public String getMensaje() {
        return mensaje;
    }

    public double getPromedio() {
        return promedio;
    }

    public StudentDTO getStudent() {
        return student;
    }


}
