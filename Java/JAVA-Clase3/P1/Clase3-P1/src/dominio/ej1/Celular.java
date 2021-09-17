package dominio.ej1;

public class Celular implements Precedable<Celular>{

    int numero;
    String titular;

    public Celular(String titular, int numero){
        this.numero=numero;
        this.titular=titular;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public int precedeA(Celular celular) {
        if (this.getNumero()<celular.getNumero()){
            return 1;
        }else if(this.getNumero()==celular.getNumero()){
            return 0;
        }
        return -1;
    }
}
