package dominio.ej2;

public class Contador {

    private double valor;

    public Contador() {
        setValor(0);
    }

    public Contador(double valor) {
        setValor(valor);
    }

    public Contador(Contador contador) {
        setValor(contador.getValor());
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void increment(double value){
        this.valor +=value;
    }

    public void decrement(double value){
        this.valor -=value;
    }

}
