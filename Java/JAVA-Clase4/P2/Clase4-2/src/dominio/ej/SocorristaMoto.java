package dominio.ej;

public class SocorristaMoto extends Socorrista<Moto> {

    public void socorrer(Moto unaMoto){
        System.out.println("Socorriendo moto "+unaMoto.getPatente());
    }
}
