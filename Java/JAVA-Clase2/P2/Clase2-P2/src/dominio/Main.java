package dominio;

import dominio.ej1.Password;
import dominio.ej1.PasswordFuerte;
import dominio.ej1.PasswordIntermedia;
import dominio.ej1.PasswordSimple;
import dominio.ej2.*;

public class Main {

    public static void main(String[] args) {
        boolean ok=false;
        //Da error por no tener num y mayus
        Password aPass = new PasswordFuerte();
        try {
            System.out.println(aPass.setValue("Hol1a111!"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Da error por no tener min de caracteres
        aPass = new PasswordIntermedia();
        try {
            System.out.println(aPass.setValue("Ho111")+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //OK
        aPass = new PasswordIntermedia();
        try {
            System.out.println(aPass.setValue("holaaa1"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        aPass = new PasswordSimple();
        try {
            System.out.println(aPass.setValue("Holaaa1"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        FiguraGeometrica circulo = new Circulo(10);
        FiguraGeometrica triangulo = new Triangulo(5,4,2);
        FiguraGeometrica rectangulo = new Rectangulo(4,3);

        FiguraGeometrica[] figuras = new FiguraGeometrica[3];
        figuras[0]=circulo;
        figuras[1]=triangulo;
        figuras[2]=rectangulo;

        System.out.println(Utils.areaPromedio(figuras));

        // write your code here
    }
}
