package dominio;

import dominio.ej.EjemploComparatorImp;
import dominio.ej.MiFactory;
import dominio.ej.Sorter;
import dominio.ej.Time;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Properties;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Object unObjeto = instanciaDinamica();
        if(unObjeto!=null) {
            //sortEjemploString(unObjeto);
            //sortEjemploInteger(unObjeto);
            generadorDeNumeros(unObjeto);
        }
    }
    public static Object instanciaDinamica(){
        //Para ver donde esta parado el proy
        //File directory = new File("./");
        //System.out.println(directory.getAbsolutePath());

        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("./src/dominio/MiFactory.properties")));
            Object anObject=MiFactory.getInstance((String) properties.get("sorter"));
             return anObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void sortEjemploString(Object instancia){
        System.out.println("Strings");
        Sorter aSorter = (Sorter)instancia;
        String[] strings = new String[4];
        strings[0]="Maria";
        strings[1]="Ana";
        strings[2]="Lala";
        strings[3]="Karen";

        Comparator<String> c = Comparator.comparing(String::toString);

        aSorter.sort(strings,c);

        for (int i = 0; i < strings.length; i++) {
            System.out.println("Nombre: "+strings[i]);
        }
    }
    public static void sortEjemploInteger(Object instancia){
        System.out.println("Integers");
        Sorter aSorter = (Sorter)instancia;
        Time timer=new Time();
        Integer[] integers = new Integer[4];
        integers[0]=4;
        integers[1]=10;
        integers[2]=1;
        integers[3]=5;

        aSorter.sort(integers,new EjemploComparatorImp());

        for (int i = 0; i < integers.length; i++) {
            System.out.println("Número: "+integers[i]);
        }
    }
    public static void generadorDeNumeros(Object instancia){
        System.out.println("10MilNumeros");
        Time timer=new Time();
        Sorter aSorter = (Sorter)instancia;
        Integer[] integers = new Integer[10000];
        for(int i=0; i<integers.length;i++){
            integers[i]= (int)(Math.random()*10);
            //System.out.println(integers[i]);
        }

        //Comparator<String> c = (a,b)->a.compareTo(b);
        System.out.println("COMIENZA");
        timer.start();
        aSorter.sort(integers,new EjemploComparatorImp());
        timer.stop();
        for (int i = 0; i < integers.length; i++) {
            System.out.println("Número: "+integers[i]);
        }
        System.out.println("Tiempo transcurrido: "+timer.elapsedTime()+" milisegundos");
    }


}
