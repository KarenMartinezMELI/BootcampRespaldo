package meli.socialMeli.exception;

import meli.socialMeli.exception.GenericException;

public class ExceptionInvalidDate extends GenericException {

    public ExceptionInvalidDate(String msg){
        super("La fecha "+msg+" es inválida");
    }
}
