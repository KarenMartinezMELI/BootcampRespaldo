package dominio;

import dominio.ej.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FuegoIndividual fuegoInfividual1 = new FuegoIndividual("JA!!!");
        FuegoIndividual fuegoInfividual2 = new FuegoIndividual("JO!!!");


        List<FuegoIndividual> fuegosInfividuales = new ArrayList<>();
        fuegosInfividuales.add(new FuegoIndividual("AH!!!"));
        fuegosInfividuales.add(new FuegoIndividual("OH!!!"));
        fuegosInfividuales.add(new FuegoIndividual("PIU!!!"));

        PackFuego pack1=null;
        try {
            pack1=new PackFuego(fuegosInfividuales,null);
            PackFuego pack2=new PackFuego(fuegosInfividuales,null);

            pack1.agregarFuego(pack2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Invitado> invitados= new ArrayList<>();
        invitados.add(new Meli("Maria"));
        invitados.add(new Meli("Rosa"));
        invitados.add(new Standard("Jose"));

        List<FuegoArtificial> fuegos = new ArrayList<>();
        fuegos.add(fuegoInfividual1);
        fuegos.add(fuegoInfividual2);
        fuegos.add(pack1);


        Evento unEvento = new Evento(invitados,fuegos);

        unEvento.soplarTorta();
	// write your code here
    }
}
