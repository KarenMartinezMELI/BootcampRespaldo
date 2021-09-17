package com.meli.tucasita.controller;

import com.meli.tucasita.dto.district.DistrictDTO;
import com.meli.tucasita.service.IDistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    private IDistrictService districtService;

    public DistrictController( IDistrictService districtService ) {
        this.districtService = districtService;
    }

    @PostMapping("/isValid")
    public ResponseEntity<?> isValid(@RequestBody @Valid DistrictDTO district){
        return new ResponseEntity<>("El distrito es válido.", HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getDistrictByName(@PathVariable() String name){
        return new ResponseEntity<>(districtService.getDistrictByName(name), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllDistricts(){
        return new ResponseEntity<>(districtService.getDistricts(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addDistrict(@RequestBody @Valid DistrictDTO district){
        return new ResponseEntity<>(districtService.addDistrict(district), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteDistrictByName(@PathVariable() String name){
        districtService.removeDistrictByName(name);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
