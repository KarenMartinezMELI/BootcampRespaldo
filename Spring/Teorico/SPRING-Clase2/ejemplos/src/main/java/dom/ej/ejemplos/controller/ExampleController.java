package dom.ej.ejemplos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping
    public String hello(){

        return "Hello";
    }

    @GetMapping("ruta/{param1}")
    public String helloMap(@PathVariable String param1){

        return "Hello "+param1;
    }
}
