package com.example.CalculadoraMetrosCuadrados.exception;

import com.example.CalculadoraMetrosCuadrados.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CalculdoraMetrosCuadradosHandlerException {

    @ExceptionHandler(NoSuchBarrioException.class)
    public ResponseEntity<ErrorDTO> noSuchBarrio(NoSuchBarrioException e) {
        var dto = new ErrorDTO( e.getClass().getCanonicalName(), e.getMessage() );
        return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorDTO> arithmeticOcurred(ArithmeticException e) {
        var dto = new ErrorDTO( e.getClass().getCanonicalName(), "Error aritmetico" );
        return new ResponseEntity(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO exceptionOcurred(Exception e) {
        e.printStackTrace();
        return new ErrorDTO( e.getClass().getCanonicalName(), "Error inesperado" );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("MethodArgumentNotValidException", e.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*@ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(HttpMessageNotReadableException e) {
        ErrorDTO error = new ErrorDTO("HttpMessageNotReadableException", "Mensaje incorrecto");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/

}