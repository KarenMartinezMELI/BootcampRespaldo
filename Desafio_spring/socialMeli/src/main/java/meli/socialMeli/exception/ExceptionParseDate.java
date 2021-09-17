package meli.socialMeli.exception;

import meli.socialMeli.exception.GenericException;

public class ExceptionParseDate extends GenericException {
    public ExceptionParseDate(String msg){
        super("Error al parsear la fecha actual "+msg);
    }
}
