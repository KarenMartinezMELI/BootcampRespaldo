package dominio;

import dominio.ej1.CuentaCorriente;
import dominio.ej4.Fraccion;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Fraccion fraccion = new Fraccion(5,2);
        Fraccion fraccion2= fraccion;

        fraccion=new Fraccion(2,3);

        System.out.println(fraccion);

        CuentaCorriente unaCuenta = new CuentaCorriente(100);
        CuentaCorriente otraCuenta = new CuentaCorriente(unaCuenta);

        System.out.println(unaCuenta.getSaldo());
        unaCuenta.transferencia(10,otraCuenta);
        System.out.println(unaCuenta.getSaldo());
        System.out.println(otraCuenta.getSaldo());


    }
}
