package meli.socialMeli.exception.user;

import meli.socialMeli.exception.GenericException;

public class ExceptionIsNotASeller extends GenericException {

    public ExceptionIsNotASeller(String msg){
        super("El usuario "+msg+" no es un vendedor");
    }
}
