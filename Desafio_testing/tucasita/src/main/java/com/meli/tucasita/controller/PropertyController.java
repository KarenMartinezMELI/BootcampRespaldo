package com.meli.tucasita.controller;

import com.meli.tucasita.dto.property.PropertyDTO;
import com.meli.tucasita.service.IPropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private IPropertyService propertyService;// = new CalculateServiceImpl(new BarrioRepository());

    public PropertyController(IPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/totalSquareFeet")
    public ResponseEntity<?> calculateTotalSquareFeet(@RequestBody @Valid PropertyDTO property){
        return new ResponseEntity<>(propertyService.getTotalSquareFeet(property),HttpStatus.OK);
    }

    @PostMapping("/totalSquareFeetUnbuilt")
    public ResponseEntity<?> calculateTotalSquareFeetUnbuilt(@RequestBody @Valid PropertyDTO property){
        return new ResponseEntity<>(propertyService.getTotalSquareFeetUnbuilt(property),HttpStatus.OK);
    }

    @PostMapping("/totalValue")
    public ResponseEntity<?> calculateTotalValue(@RequestBody @Valid PropertyDTO property){
        return new ResponseEntity<>(propertyService.getTotalValue(property),HttpStatus.OK);
    }

    @PostMapping("/biggestEnvironment")
    public ResponseEntity<?> calculateBiggestEnvironment(@RequestBody @Valid PropertyDTO property){
        return new ResponseEntity<>(propertyService.getBiggestEnvironment(property),HttpStatus.OK);
    }

    @PostMapping("/environments/totalSquareFeet")
    public ResponseEntity<?> calculateEnvironmentsTotalSquareFeet(@RequestBody @Valid PropertyDTO property){
        return new ResponseEntity<>(propertyService.getEnvironmentsTotalSquareFeet(property),HttpStatus.OK);
    }


}
