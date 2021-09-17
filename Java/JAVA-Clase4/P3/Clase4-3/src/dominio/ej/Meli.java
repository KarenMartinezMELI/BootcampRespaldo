package dominio.ej;

public class Meli extends Invitado{



    public Meli(String unNombre){
        this.nombre=unNombre+"(Meli)";
    }
    @Override
    public void comerTorta() {
        System.out.println(this+": Viva la Chiqui!!");
    }

    public String toString(){
        return this.nombre;
    }
}
