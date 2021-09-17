package com.example.CalculadoraMetrosCuadrados.repository;

import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.model.Barrio;

public interface IBarrioRepository {
    Barrio getBarrioPorNombre(String name) throws NoSuchBarrioException;

}
