package meli.socialMeli.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import meli.socialMeli.dto.GenericResponseDTO;
import meli.socialMeli.exception.GenericException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionMiddleware {

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        return new ResponseEntity<>(new HashMap<>() {{
            put("error_code", "system error");
            put("message", ex.getMessage());
        }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new GenericResponseDTO(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<Object> handleInvalidFormatException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new GenericResponseDTO("Formato de Modelo enviado inválido: "+ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { GenericException.class })
    public ResponseEntity<Object> handleOwnExceptions(GenericException ex) {
        return new ResponseEntity<>(new GenericResponseDTO(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}

