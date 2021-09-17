package dom.ej.clase22.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;


public class Calculator {

    public static int ageByDate(int day, int month, int year){
        int age=0;
        LocalDate start = LocalDate.of( year , month , day) ;
        LocalDate stop = LocalDate.now( ZoneId.of( "America/Montevideo" ) );
        int years = (int) java.time.temporal.ChronoUnit.YEARS.between( start , stop );

        return years;
    }

    public static int ageByDate2(int day, int month, int year){
        int age=0;
        LocalDate start = LocalDate.of( year , month , day) ;
        Period stop = Period.between(start,LocalDate.now());
        int years = stop.getYears();

        return years;
    }
}
