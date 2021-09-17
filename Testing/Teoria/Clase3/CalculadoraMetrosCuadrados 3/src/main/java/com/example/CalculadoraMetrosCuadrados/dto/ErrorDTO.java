package com.example.CalculadoraMetrosCuadrados.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ErrorDTO {

    private final String code;
    private final String message;

    public ErrorDTO(@NonNull String code, @NonNull String message) {
        this.code = code;
        this.message = message;
    }
}