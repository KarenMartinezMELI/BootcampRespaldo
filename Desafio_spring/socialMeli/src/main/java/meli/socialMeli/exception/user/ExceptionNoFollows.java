package meli.socialMeli.exception.user;

import meli.socialMeli.exception.GenericException;

public class ExceptionNoFollows extends GenericException {

    public ExceptionNoFollows(String msg){
        super("El usuario "+msg+" no puede dejar de seguir al solicitado ya que no lo estaba siguiendo");
    }
}
