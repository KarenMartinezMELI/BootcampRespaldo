package dominio.ej4;

public class Fraccion {
    private int numerador;
    private int denominador;

    public Fraccion(int superior, int inferior) {
        setNumerador(superior);
        setDenominador(inferior);
    }

    //Generamos enteros con este constructor
    public Fraccion(int entero) {
        setNumerador(entero);
        setDenominador(1);
    }

    public Fraccion(Fraccion fraccion) {
        setNumerador(fraccion.getNumerador());
        setDenominador(fraccion.getDenominador());
    }

    public Fraccion() {
        setNumerador(1);
        setDenominador(1);
    }

    public static Fraccion sumar(Fraccion fraccion, Fraccion fraccion2){
        int denominador= fraccion.getDenominador()* fraccion2.getDenominador();
        int numerador= (fraccion.getNumerador()* fraccion2.getDenominador())+(fraccion2.getNumerador()* fraccion.getDenominador());

        return new Fraccion(denominador,numerador);
    }
    public static Fraccion restar(Fraccion fraccion, Fraccion fraccion2){
        int denominador= fraccion.getDenominador()* fraccion2.getDenominador();
        int numerador= (fraccion.getNumerador()* fraccion2.getDenominador())-(fraccion2.getNumerador()* fraccion.getDenominador());
        return new Fraccion(denominador,numerador);
    }
    public static Fraccion multiplicar(Fraccion fraccion, Fraccion fraccion2){
        int denominador= fraccion.getDenominador()* fraccion2.getDenominador();
        int numerador= fraccion.getNumerador()* fraccion2.getDenominador();
        return new Fraccion(denominador,numerador);
    }
    public static Fraccion dividir(Fraccion fraccion, Fraccion fraccion2){
       return Fraccion.multiplicar(fraccion,new Fraccion(fraccion2.getDenominador(),fraccion2.getNumerador()));
    }

    public int getNumerador() {
        return numerador;
    }

    public void setNumerador(int numerador) {
        this.numerador = numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public void setDenominador(int denominador) {
        this.denominador = denominador;
    }

    public String toString(){
        return getNumerador()+"/"+getDenominador();
    }

    public Fraccion clone(){
        return new Fraccion(this);
    }
}
