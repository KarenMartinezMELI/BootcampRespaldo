package dominio.ej;

public class Time {
    private long inicio;
    private long fin;


    public void start(){
        inicio=System.currentTimeMillis();
    }

    public void stop(){
        fin=System.currentTimeMillis();
    }

    public long elapsedTime(){
        return fin-inicio;
    }

}
