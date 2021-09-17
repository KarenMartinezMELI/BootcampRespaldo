package com.ej.calories.dto;

import java.util.List;

public class PlateProcDTO {
    String name;
    List<IngredientWCalorieDTO> ingredients;
    double calories;


    public PlateProcDTO(String name, List<IngredientWCalorieDTO> ingredients, double calories) {
        this.name = name;
        this.ingredients = ingredients;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }
    public double getCalories() {
        return calories;
    }
    public List<IngredientWCalorieDTO> getIngredients() {
        return ingredients;
    }
}
