package com.example.CalculadoraMetrosCuadrados.repository;

import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Neighborhood;

public interface IBarrioRepository {
    Neighborhood getBarrioPorNombre(String name) throws NoSuchBarrioException;

}
