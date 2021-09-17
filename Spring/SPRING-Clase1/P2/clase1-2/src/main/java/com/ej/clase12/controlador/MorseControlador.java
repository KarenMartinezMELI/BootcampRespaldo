package com.ej.clase12.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ej.clase12.model.MorseLogic;

@RestController
public class MorseControlador {

    @GetMapping(path="morseToAlpha/{string}")
    public String alphaReturn(@PathVariable String string){
        try {
            return MorseLogic.morseToAlpha(string);
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

}
