package dominio.ej;

import java.util.ArrayList;
import java.util.List;

public class PackFuego implements FuegoArtificial{
    List<FuegoIndividual> fuegosIndividuales;
    List<PackFuego> packFuegos;


    public PackFuego(List<FuegoIndividual> fuegosIndividuales, List<PackFuego> packs) throws Exception {
        if(fuegosIndividuales.isEmpty()){
            throw new Exception("Un pack debe contener al menos 1 pack de fuegos individuales");
        }
        this.fuegosIndividuales=fuegosIndividuales;
        if(packs!=null) {
            this.packFuegos = packs;
        }
    }
    public void agregarFuego(FuegoArtificial fuego){
        if(fuego instanceof FuegoIndividual){
            fuegosIndividuales.add((FuegoIndividual) fuego);
        }else if(fuego instanceof PackFuego){
            if(packFuegos==null) {
                packFuegos=new ArrayList<>();
            }
            packFuegos.add((PackFuego) fuego);
        }
    }


    public List<FuegoArtificial> getFuegosArtificiales(){
        List<FuegoArtificial> fuegos= new ArrayList<>();

        for(FuegoIndividual f:fuegosIndividuales){
            fuegos.add(f);
        }
        for(PackFuego f:packFuegos){
            fuegos.add(f);
        }

        return fuegos;
    }

    @Override
    public void explotar() {
        for(FuegoIndividual f:fuegosIndividuales)
        {
            f.explotar();
        }
        if(packFuegos!=null) {
            for (PackFuego f : packFuegos) {
                explotarRecursivo(f);
            }
        }
    }

    private void explotarRecursivo(PackFuego packFuego){
        for(FuegoIndividual f:packFuego.fuegosIndividuales)
        {
            f.explotar();
        }
        if(packFuego.packFuegos!=null) {
            for (PackFuego f : packFuego.packFuegos) {
                explotarRecursivo(f);
            }
        }

    }
}
