package com.ej.calories.service;

import com.ej.calories.dto.IngredientDTO;
import com.ej.calories.dto.IngredientWCalorieDTO;
import com.ej.calories.dto.PlateDTO;
import com.ej.calories.dto.PlateProcDTO;

import java.util.List;

public interface ICalorieService {

    double getCalories(PlateDTO plate);
    List<IngredientWCalorieDTO> getIngredientsCalories(PlateDTO plate);
    IngredientDTO getIngredientWMoreCalories(PlateDTO plate);
    List<PlateProcDTO> getPlatesProcessed(List<PlateDTO> plates);
}
