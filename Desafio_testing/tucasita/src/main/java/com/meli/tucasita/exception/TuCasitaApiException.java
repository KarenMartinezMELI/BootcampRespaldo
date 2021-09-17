package com.meli.tucasita.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TuCasitaApiException extends RuntimeException{
    private HttpStatus status;
    public TuCasitaApiException(String msg,HttpStatus status) {
        super( msg);
        this.status=status;
    }

}
