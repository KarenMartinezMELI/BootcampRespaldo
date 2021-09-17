package com.ej.calories.ApiMiddlewareException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

    @ControllerAdvice
    public class ApiExceptionMiddleware extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = { Exception.class })
        public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
            return new ResponseEntity<>(new HashMap<>() {{
                put("error_code", "system error");
                put("message", ex.getMessage());
            }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }


        @ExceptionHandler(value = { RuntimeException.class })
        public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex, WebRequest request) {
            return new ResponseEntity<>(new HashMap<>() {{
                put("message", ex.getMessage());
            }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

