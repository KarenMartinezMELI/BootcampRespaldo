package com.ej.calories.dto;

public class IngredientWCalorieDTO {
    IngredientDTO ingredient;
    double calories;


    public IngredientWCalorieDTO(IngredientDTO ingredient, double calories){
        this.ingredient=ingredient;
        this.calories=calories;
    }

    public double getCalories() {
        return calories;
    }

    public IngredientDTO getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "ingredient=" + ingredient +
                ", calories=" + calories +
                '}';
    }
}
