package com.meli.tucasita.exception;

import com.meli.tucasita.dto.ErrorDTO;
import com.meli.tucasita.exception.TuCasitaApiException;
import com.meli.tucasita.exception.TuCasitaApiListException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MiddlewareException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO exceptionOcurred(Exception e) {
        return new ErrorDTO( e.getClass().getCanonicalName(), "Error inesperado" );
    }

    @ExceptionHandler(TuCasitaApiException.class)
    ResponseEntity<ErrorDTO> handleGlobalExceptions(TuCasitaApiException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getClass().getCanonicalName(), e.getMessage()),e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<ErrorDTO>> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<ErrorDTO> errors = new ArrayList<>();
        for(ObjectError er :e.getAllErrors()){
           errors.add(new ErrorDTO(er.getClass().getCanonicalName(),er.getDefaultMessage()));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TuCasitaApiListException.class)
    protected ResponseEntity<List<ErrorDTO>> handleValidationListExceptions(TuCasitaApiListException e) {
        List<ErrorDTO> errors = new ArrayList<>();
        for(TuCasitaApiException er :e.getExceptionList()){
            errors.add(new ErrorDTO(er.getClass().getCanonicalName(),er.getMessage()));
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO(e.getClass().getCanonicalName(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
