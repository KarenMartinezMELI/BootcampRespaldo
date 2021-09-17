package com.meli.tucasita.exception.district;

import com.meli.tucasita.exception.TuCasitaApiException;
import org.springframework.http.HttpStatus;

public class DistrictWNameAlreadyExistsException extends TuCasitaApiException {

    public DistrictWNameAlreadyExistsException(String barrio) {
        super( barrio + " ya existe.", HttpStatus.CONFLICT);
    }

}
