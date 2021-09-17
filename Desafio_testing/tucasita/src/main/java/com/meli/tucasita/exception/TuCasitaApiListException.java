package com.meli.tucasita.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class TuCasitaApiListException extends RuntimeException{

    @Getter
    List<TuCasitaApiException> exceptionList;
}
