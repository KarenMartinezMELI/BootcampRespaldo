package dominio.ej5;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

    private GregorianCalendar calendar;


    public Fecha(int dia, int mes, int anio){

        calendar=new GregorianCalendar(anio,  mes,  dia);
    }
    public int getDia() {

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setDia(int dia) {

        calendar.set(Calendar.DAY_OF_MONTH,dia);
    }

    public int getMes() {

        return calendar.get(Calendar.MONTH);
    }

    public void setMes(int mes) {

        calendar.set(Calendar.MONTH,mes);
    }

    public int getAnio() {

        return calendar.get(Calendar.YEAR);
    }

    public void setAnio(int anio) {

        calendar.set(Calendar.YEAR,anio);
    }

    public void incrementarDia(){
        calendar.add(Calendar.DAY_OF_MONTH,1);
    }

    public boolean isValido(){
        try {
            LocalDate ld = LocalDate.of( getAnio() , getMes() , getDia() ) ;
            return true ;
        } catch ( DateTimeParseException e ) {
            return false;
        }
    }

    public String toString(){
        return getDia()+"/"+getMes()+"/"+getAnio();
    }


}
