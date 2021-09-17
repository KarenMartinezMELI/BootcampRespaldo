package meli.socialMeli.exception.user;

import meli.socialMeli.exception.GenericException;

public class ExceptionUserNotExist extends GenericException {

    public ExceptionUserNotExist(String msg){
        super("Un usuario que intenta acceder no existe: "+msg);
    }
}
