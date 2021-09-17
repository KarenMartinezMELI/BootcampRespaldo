package dominio.ej;

public class SocorristaAuto extends Socorrista<Auto> {


    public void socorrer(Auto unAuto){
        System.out.println("Socorriendo auto "+unAuto.getPatente());
    }

}
