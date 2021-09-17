package meli.socialMeli.exception;

public class ExceptionNegativeValue extends GenericException {

    public ExceptionNegativeValue(String msg){
        super("El valor no puede ser negativo: "+msg);
    }
}
