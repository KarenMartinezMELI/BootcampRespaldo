package com.ej.calories.repository;

import com.ej.calories.dto.ErrorResponseDTO;
import com.ej.calories.model.Ingredient;

import java.util.List;

public interface IIngredientRepository {

    public List<Ingredient> getIngredients();
    public Ingredient findByName(String name) throws ErrorResponseDTO;
}
