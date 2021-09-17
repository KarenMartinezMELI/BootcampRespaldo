package dominio.ej1;

import java.util.HashMap;

public class CuentaCorriente {

    private double saldo;
    HashMap<Integer,Double> transferenciaMontos;

    public CuentaCorriente(){
        setSaldo(0);
        transferenciaMontos =new HashMap<>();
    }

    public CuentaCorriente(double saldo){
        transferenciaMontos =new HashMap<>();
        setSaldo(saldo);
    }

    public CuentaCorriente(CuentaCorriente cuentaCorriente){
        transferenciaMontos =(HashMap<Integer, Double>) cuentaCorriente.transferenciaMontos.clone();
        setSaldo(cuentaCorriente.getSaldo());
    }

    public void ingreso(double cantidad){
        setSaldo(getSaldo()+cantidad);
    }
    public boolean egreso(double cantidad){
        if(cantidad<=getSaldo()) {
            setSaldo(getSaldo() - cantidad);
            return true;
        }else{
           return false;
        }
    }
    public void reintegro(double saldo)
    {
        this.saldo+=saldo;
    }

    public void transferencia(double saldo,CuentaCorriente destino)
    {
        this.saldo-=saldo;
        destino.saldo+=saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
