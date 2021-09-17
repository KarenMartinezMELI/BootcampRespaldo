package meli.socialMeli.exception.user;

import meli.socialMeli.exception.GenericException;

public class ExceptionSameUser extends GenericException {

    public ExceptionSameUser(String msg){
        super("El usuario "+msg+" no puede realizar dicha operacion a si mismo");
    }
}
