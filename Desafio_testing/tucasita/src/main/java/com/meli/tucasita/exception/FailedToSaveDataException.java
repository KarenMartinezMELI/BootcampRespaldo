package com.meli.tucasita.exception;

import org.springframework.http.HttpStatus;

public class FailedToSaveDataException extends TuCasitaApiException {

    public FailedToSaveDataException(String msg) {
        super( "Ha ocurrido un error al guardar los datos. "+msg, HttpStatus.BAD_REQUEST);
    }

}
