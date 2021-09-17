package com.ej.calories.controller;


import com.ej.calories.dto.PlateDTO;
import com.ej.calories.service.ICalorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Calorie")
public class CalorieController {

    private final ICalorieService calorieService;

    public CalorieController(ICalorieService calorieService){
        this.calorieService=calorieService;
    }

    @PostMapping(path="/PlateCalories")
    public ResponseEntity<?> calc(@RequestBody PlateDTO plate){
      return new ResponseEntity<String>("Las calorias del plato son: "+calorieService.getCalories(plate), HttpStatus.OK);
    }


    @PostMapping(path="/IngredientsWCalories")
    public ResponseEntity<?> calcIngredientsWCalories(@RequestBody PlateDTO plate){
      return new ResponseEntity<>(calorieService.getIngredientsCalories(plate), HttpStatus.OK);
    }

    @PostMapping(path="/IngredientWMostCalories")
    public ResponseEntity<?> returnIngredientWMostCalories(@RequestBody PlateDTO plate){
        return new ResponseEntity<>(calorieService.getIngredientWMoreCalories(plate), HttpStatus.OK);
    }

    @PostMapping(path="/PlatesProc")
    public ResponseEntity<?> returnPlatesProc(@RequestBody List<PlateDTO> plates){
        return new ResponseEntity<>(calorieService.getPlatesProcessed(plates), HttpStatus.OK);
    }
}
