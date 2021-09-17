package com.meli.tucasita.exception.district;

import com.meli.tucasita.exception.TuCasitaApiException;
import org.springframework.http.HttpStatus;

public class NoSuchDistrictNameException extends TuCasitaApiException {

    public NoSuchDistrictNameException(String barrio) {
        super( barrio + " no ha sido encontrado.", HttpStatus.NOT_FOUND);
    }

}
