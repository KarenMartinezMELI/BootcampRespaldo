package dominio.ej;

public class Prenda {
    String marca;
    String modelo;

    public Prenda(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String toString(){
        return "Ropa: "+getMarca() +", "+ getModelo();
    }
}
