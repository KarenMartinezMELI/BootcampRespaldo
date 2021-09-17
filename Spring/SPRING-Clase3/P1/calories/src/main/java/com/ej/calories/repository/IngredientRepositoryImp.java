package com.ej.calories.repository;

import com.ej.calories.dto.ErrorResponseDTO;
import com.ej.calories.model.Ingredient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Repository
public class IngredientRepositoryImp implements IIngredientRepository{

    private List<Ingredient> ingredients;

    public IngredientRepositoryImp(){
        ingredients=loadFile("food.json");
    }

    public List<Ingredient> getIngredients(){
        return ingredients;
    }

    public Ingredient findByName(String name) throws ErrorResponseDTO {
        return this.ingredients.stream()
                .filter( ingredient -> ingredient.getName().equals(name) )
                .findFirst().orElseThrow( () -> new ErrorResponseDTO("Hay un ingrediente que no existe:"+name) );
    }

    private List<Ingredient> loadFile(String fileName)
    {
        List<Ingredient> ret = null;

        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:"+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();
        //TypeReference<List<BarrioDTO>> typeRef = new TypeReference<>() {};

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<Ingredient>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


}
