package meli.socialMeli.exception.user;

import meli.socialMeli.exception.GenericException;

public class ExceptionAlreadyFollows extends GenericException {

    public ExceptionAlreadyFollows(String msg){
        super("El usuario "+msg+" ya está siguiendo al usuario seleccionado");
    }
}
