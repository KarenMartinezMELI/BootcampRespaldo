package com.example.demo.controllers;

import com.example.demo.domain.Empleado;
import com.example.demo.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping("")
    public Empleado save(@RequestBody Empleado empleado){
        return empleadoService.save(empleado);
    }

    @GetMapping("")
    public List<Empleado> findByAge(@RequestParam(required=false) Long edad){
        System.out.println("HOLA");
        return empleadoService.findByAge(edad);
    }

}
