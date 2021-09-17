package dominio.ej2;

import dominio.ej2.FiguraGeometrica;

public class Circulo extends FiguraGeometrica {

    private double radio;

    public Circulo(double radio){
        setRadio(radio);
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    @Override
    public double area() {
        return Math.PI*Math.pow(radio,2);
    }
}
