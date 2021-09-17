package dominio.ej;

public class FuegoIndividual implements FuegoArtificial{
    private String ruido;

    public FuegoIndividual(String ruido){
        this.ruido=ruido;
    }

    @Override
    public void explotar() {
        System.out.println(this.ruido);
    }
}
