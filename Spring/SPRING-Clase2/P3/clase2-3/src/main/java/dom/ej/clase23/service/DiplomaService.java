package dom.ej.clase23.service;
import dom.ej.clase23.dto.*;
import java.util.ArrayList;
import java.util.List;


public class DiplomaService implements IDiplomaService{

    public static double aprobacion=5;
    public static double rangoMin=0;
    public static double rangoMax=10;
    public static double congratsNote=9;

    public DiplomaDTO getDiploma(StudentDTO student) throws Exception {
        double promedio=returnAvg(student);
        String mensaje="Se ha recibido";
        if(promedio>congratsNote){
            mensaje+=" con muchas felicitaciones.";
        }else{
            mensaje+=".";
        }
        DiplomaDTO diploma = new DiplomaDTO(student,mensaje,promedio);
        return diploma;
    }


    private double returnAvg(StudentDTO student) throws Exception {
        double calc=0;
        List<SubjectDTO> subs= student.getSubjects();
        List<SubjectDTO> subsError=new ArrayList<>();

        for(SubjectDTO s:subs){
            calc+=s.getScore();
            if(s.getScore()<rangoMin||s.getScore()>rangoMax){
                subsError.add(s);
            }
        }

        if(!subs.isEmpty()) {
            calc = calc / subs.size();
        }
        if(!subsError.isEmpty()){
            throw new Exception("Posee materias con rangos inválidos de notas. No se puede generar el diploma: "+subsError.toString());
        }
        if(calc<aprobacion){
            throw new Exception("No se puede entregar el diploma ya que el promedio es inferior a "+aprobacion+", siendo el promedio de "+calc);
        }
        return calc;
    }


}
