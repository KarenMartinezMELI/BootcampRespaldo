package dominio.ej;

public class Moto extends Vehiculo{
    

    public Moto(int velocidad, int aceleracion, int anguloDeGiro, String patente){
        this.velocidad=velocidad;
        this.aceleracion =aceleracion;
        this.anguloDeGiro=anguloDeGiro;
        this.patente=patente;

        this.peso=300;
        this.ruedas=2;
    }
}
