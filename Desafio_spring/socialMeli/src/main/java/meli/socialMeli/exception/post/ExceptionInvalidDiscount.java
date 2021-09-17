package meli.socialMeli.exception.post;

import meli.socialMeli.exception.GenericException;

public class ExceptionInvalidDiscount extends GenericException {

    public ExceptionInvalidDiscount(String msg){
        super("El descuento otorgado es inválido. Debe ser mayor a 0 y menor a 1: "+msg);
    }
}
