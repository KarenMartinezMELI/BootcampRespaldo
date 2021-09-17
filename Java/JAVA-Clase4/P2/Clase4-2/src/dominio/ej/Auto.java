package dominio.ej;

public class Auto extends Vehiculo {

    public Auto(int velocidad, int aceleracion, int anguloDeGiro, String patente){
        this.velocidad=velocidad;
        this.aceleracion =aceleracion;
        this.anguloDeGiro=anguloDeGiro;
        this.patente=patente;

        this.peso=1000;
        this.ruedas=4;
    }
}
