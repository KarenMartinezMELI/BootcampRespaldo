package dominio.ej;

import java.util.List;

public class Evento {
    private List<Invitado> invitados;
    private List<FuegoArtificial> fuegosArtificiales;

    public Evento(List<Invitado> invitados,List<FuegoArtificial> fuegosArtificiales){
        System.out.println("Prepárense que el evento se acabó de inicializar!");
        System.out.println();
        this.invitados=invitados;
        this.fuegosArtificiales=fuegosArtificiales;
    }

    public List<FuegoArtificial> getFuegosArtificiales() {
        return fuegosArtificiales;
    }

    private void distribuirPorcionesDeTorta(){
        System.out.println("Se reparten las porciones de torta!");
        for(Invitado i:getInvitados()){
            i.comerTorta();
        }
        System.out.println();
    }

    private void explotarFuegos(){
        System.out.println("Se explotan los fuegos artificiales!");
        for(FuegoArtificial f:getFuegosArtificiales()){
            f.explotar();
        }
        System.out.println();
    }

    public void soplarTorta(){
        System.out.println("La Chiqui sopla las velas!---------");
        distribuirPorcionesDeTorta();
        explotarFuegos();
        System.out.println("---------");
    }

    private List<Invitado> getInvitados(){
        return this.invitados;
    }
}
