package meli.socialMeli.util;

import org.apache.tomcat.util.json.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateValidator {

    private GregorianCalendar calendar;


    public DateValidator(int dia, int mes, int anio){

        calendar=new GregorianCalendar(anio,  mes,  dia);
    }
    public int getDay() {

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setDay(int dia) {

        calendar.set(Calendar.DAY_OF_MONTH,dia);
    }

    public int getMonth() {

        return calendar.get(Calendar.MONTH);
    }

    public void setMonth(int mes) {

        calendar.set(Calendar.MONTH,mes);
    }

    public int getYear() {

        return calendar.get(Calendar.YEAR);
    }

    public void setYear(int anio) {

        calendar.set(Calendar.YEAR,anio);
    }

    public static boolean isValid(Date date){
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            DateValidator calendar= new DateValidator(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
            LocalDate ld = LocalDate.of( calendar.getYear() , calendar.getMonth() , calendar.getDay() ) ;
        return true ;
        } catch ( DateTimeParseException e ) {
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public String toString(){
        return getDay()+"/"+getMonth()+"/"+getYear();
    }


}
