package dominio;

import dominio.ej.GuardaRopa;
import dominio.ej.Prenda;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GuardaRopa guardaRopa = new GuardaRopa();

        List<Prenda> prendas = new ArrayList<Prenda>();
        prendas.add(new Prenda("UnaMarca","UnModelo"));
        prendas.add(new Prenda("UnaMarca2","UnModelo2"));

        int reserva=guardaRopa.guardarPrendas(prendas);

        System.out.println("Prendas guardadas ");
        guardaRopa.mostrarPrendas();

        System.out.println();

        System.out.println("Prendas retornadas con la reserva "+reserva);
        try {
            for(Prenda p:guardaRopa.devolverPrendas(reserva)){
                System.out.println(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
