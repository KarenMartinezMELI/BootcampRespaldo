package com.meli.tucasita.exception.property;

import com.meli.tucasita.exception.TuCasitaApiException;
import org.springframework.http.HttpStatus;

public class EnviromentsBiggerThanPropertyException extends TuCasitaApiException {

    public EnviromentsBiggerThanPropertyException(String msg) {
        super("La propiedad tiene una diferencia de "+msg+" en relación a sus ambientes. Debería ser mayor o igual a 0.", HttpStatus.NOT_FOUND);
    }

}
