package dominio.ej2;

import dominio.ej2.FiguraGeometrica;

public class Rectangulo extends FiguraGeometrica {
    public double base;
    public double altura;

    public Rectangulo(double base, double altura){
        setBase(base);
        setAltura(altura);
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public double area() {
        return (base*altura)/2;
    }
}
