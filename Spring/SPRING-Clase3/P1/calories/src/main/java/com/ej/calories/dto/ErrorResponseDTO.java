package com.ej.calories.dto;

public class ErrorResponseDTO extends RuntimeException {

    public ErrorResponseDTO(String n){
        super(n);
    }

}
