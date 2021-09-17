package com.ej.calories.model;

import lombok.Data;
import lombok.AllArgsConstructor;
@Data
public class Ingredient{
    String name;
    int calories;

    public Ingredient(String name, int weight){
        this.name=name;
        this.calories=weight;
    }

    public Ingredient(){

    }

    public String getName() {
        return name;
    }
    public int getCalories() {
        return calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
