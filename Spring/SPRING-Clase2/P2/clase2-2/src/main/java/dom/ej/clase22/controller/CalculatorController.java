package dom.ej.clase22.controller;

import dom.ej.clase22.model.Calculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class CalculatorController {

    @GetMapping(path="/{day}/{month}/{year}")
    public String calcDateToAge(@PathVariable int day,@PathVariable int month,@PathVariable int year){

        return "Tiene: " +Calculator.ageByDate(day,month,year)+" años :).";
    }

}