package meli.socialMeli.exception;

public class ExceptionInvalidParameter extends RuntimeException{

    public ExceptionInvalidParameter(String msg){
        super("Parámetros en la url de la request inválidos: "+msg);
    }
}
