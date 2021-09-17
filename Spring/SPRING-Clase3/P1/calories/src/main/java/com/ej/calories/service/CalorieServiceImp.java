package com.ej.calories.service;

import com.ej.calories.dto.*;
import com.ej.calories.model.Ingredient;
import com.ej.calories.repository.IIngredientRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class CalorieServiceImp implements ICalorieService{

    private final IIngredientRepository ingredientRepository;
    static private int calPerGram=100;

    public CalorieServiceImp(IIngredientRepository ingredientRepository){
        this.ingredientRepository=ingredientRepository;
    }

    public double getCalories(PlateDTO plate){
        double calories=0;
        for(IngredientDTO ing:plate.getIngredients()){
            calories+=(ing.getWeight()/calPerGram)*getIngredientRepository().findByName(ing.getName()).getCalories();
        }
        return calories;
    }

    public IIngredientRepository getIngredientRepository(){
        return this.ingredientRepository;
    }

    public List<PlateProcDTO> getPlatesProcessed(List<PlateDTO> plates){
        List<PlateProcDTO> proc = new ArrayList<>();
        for (PlateDTO p:plates){
            proc.add(new PlateProcDTO(p.getName(),getIngredientsCalories(p),getCalories(p)));
        }
        return proc;
    }


    public List<IngredientWCalorieDTO> getIngredientsCalories(PlateDTO plate){
        List<IngredientWCalorieDTO> ingredients=new ArrayList<>();
        double calories=0;
        for(IngredientDTO ing:plate.getIngredients()){
            calories=(ing.getWeight()/calPerGram)*getIngredientRepository().findByName(ing.getName()).getCalories();
            ingredients.add(new IngredientWCalorieDTO(ing,calories));

        }
        return ingredients;
    }

    public IngredientDTO getIngredientWMoreCalories(PlateDTO plate){
        double calories=0;
        double actualCalorie=0;
        IngredientDTO ingredient=null;

        for(IngredientDTO ing:plate.getIngredients()){
            calories=(ing.getWeight()/calPerGram)*getIngredientRepository().findByName(ing.getName()).getCalories();
            if(ingredient==null||calories>actualCalorie){
                actualCalorie=calories;
                ingredient=ing;
            }


        }
        if(ingredient==null){
            throw new ErrorResponseDTO("la lista es vacia");
        }
        return ingredient;
    }


}
