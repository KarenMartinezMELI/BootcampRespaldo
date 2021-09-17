package com.ej.calories.dto;

import java.util.List;

public class PlateDTO {
    String name;
    List<IngredientDTO> ingredients;

    public String getName() {
        return name;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }
}
