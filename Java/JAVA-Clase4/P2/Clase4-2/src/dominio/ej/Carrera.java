package dominio.ej;

import java.lang.reflect.Constructor;
import java.util.List;

public class Carrera {
    private int distancia;
    private int premioEnDolares;
    private String nombre;
    private int cantidadDeVehiculosPermitidos;
    private List<Vehiculo> vehiculos;
    private SocorristaMoto socorristaMoto;
    private  SocorristaAuto socorristaAuto;

    public void darDeAlta(int velocidad,int aceleracion, int anguloDeGiro, String patente,String type) throws Exception {
        if(vehiculos.size()<=cantidadDeVehiculosPermitidos) {
            try {
                Class o = Class.forName(type);
                Constructor cons = o.getConstructor();

                Object finalObject = cons.newInstance(velocidad, aceleracion, anguloDeGiro, patente);
                vehiculos.add((Vehiculo) finalObject);
            }catch(Exception e){
                throw new Exception("No se pudo crear el vehículo: tipo de vehículo no válido");
            }
        }else{
            throw new Exception("No se puede dar de alta: Cantidad superada");
        }
    }

    public void eliminarVehiculo(Vehiculo vehiculo){
        vehiculos.remove(vehiculo);
    }
    public void eliminarVehiculoConPatente(String unaPatente){
        for(Vehiculo v:vehiculos){
            if(v.getPatente().equals(unaPatente)){
                eliminarVehiculo(v);
            }
        }
    }

    public void getGanador(){
        double cal=0;
        Vehiculo vehiculo=null;
        double valEnd=0;

        for(Vehiculo v:vehiculos){
            if(valEnd<cal||vehiculo==null){
                cal=v.getVelocidad() * (v.getAceleracion()/2)/(v.getAnguloDeGiro()*(v.getPeso()-v.getRuedas()*100));
                vehiculo=v;
            }
        }
        System.out.println("Gana vehículo "+vehiculo+" con "+valEnd+" puntos!");
    }

    private Vehiculo retornarVehiculoConPatente(String unaPatente) throws Exception{
        for(Vehiculo v:vehiculos){
            if(v.getPatente().equals(unaPatente)){
                return v;
            }
        }
        throw new Exception("No se puede socorrer: No existe el vehiculo de dicho tipo.");
    }


    public void socorrer(String patente){
        try {
            Vehiculo vehiculo = retornarVehiculoConPatente(patente);
            if(vehiculo instanceof Auto) {
                socorristaAuto.socorrer((Auto) vehiculo);
            }else if(vehiculo instanceof Moto){
                socorristaMoto.socorrer((Moto) vehiculo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
