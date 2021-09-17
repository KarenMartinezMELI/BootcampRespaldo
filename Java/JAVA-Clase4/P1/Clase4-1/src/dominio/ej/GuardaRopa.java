package dominio.ej;

import java.util.HashMap;
import java.util.List;

public class GuardaRopa {
    private HashMap<Integer,List<Prenda>> diccionario;
    private Contador contador;

    public GuardaRopa(HashMap<Integer, List<Prenda>> diccionario, Contador contador) {
        this.diccionario = diccionario;
        this.contador = contador;
    }

    public GuardaRopa() {
        this.diccionario = new HashMap<>();
        this.contador = new Contador();
    }

    public Integer guardarPrendas(List<Prenda> listaDePrenda){
        int valor=contador.getValor();
        diccionario.put(valor,listaDePrenda);
        contador.incrementar();
        return valor;
    }

    public void mostrarPrendas(){
        for(Integer num:diccionario.keySet()){
            System.out.print(num+" -");
            for(Prenda p:diccionario.get(num)){
                System.out.print(" "+p+"|");
            }
            System.out.println();
        }
    }

    public List<Prenda> devolverPrendas(Integer numero) throws Exception{
        if(diccionario.containsValue(numero)) {
            List<Prenda> prendas = diccionario.get(numero);
            diccionario.remove(numero);
            return prendas;
        }
        throw new Exception("La reserva no existe");
    }
}
