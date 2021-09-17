package dominio;

import dominio.ej1.Celular;
import dominio.ej1.Persona;
import dominio.ej1.SortUtil;

public class Main {

    public static void main(String[] args) {

        System.out.println("Personas");
        Persona p1 = new Persona("Nana", 123);
        Persona p2 = new Persona("Nana2", 323323);
        Persona p3 = new Persona("Nana3", 113);
        Persona p4 = new Persona("Nana4", 1423);

        Persona[] personas = new Persona[4];
        personas[0] = p1;
        personas[1] = p2;
        personas[2] = p3;
        personas[3] = p4;

        SortUtil.ordenar(personas);

        for (int i = 0; i < personas.length; i++) {
            System.out.println(personas[i].getDni());
        }


        System.out.println("Celulares");
        Celular c1 = new Celular("Nana", 123);
        Celular c2 = new Celular("Nana2", 323);
        Celular c3 = new Celular("Nana3", 413);
        Celular c4 = new Celular("Nana4", 1423);

        Celular[] celulares = new Celular[4];
        celulares[0] = c1;
        celulares[1] = c2;
        celulares[2] = c3;
        celulares[3] = c4;

        SortUtil.ordenar(celulares);

        for (int i = 0; i < celulares.length; i++) {
            System.out.println(celulares[i].getNumero());
        }
        // write your code here
    }
}
