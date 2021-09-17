package com.ej.clase1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ej.clase1.model.Roman;

@RestController
public class RomanController {

    @GetMapping(path="decimalToRoman/{number}")
    public String romanReturn(@PathVariable int number){

        try {
            return Roman.convertDecimalToRoman(number);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(path="romanToDecimal/{string}")
    public int romanReturn(@PathVariable String string){
        return Roman.convertRomanToDecimal(string);
    }

}
