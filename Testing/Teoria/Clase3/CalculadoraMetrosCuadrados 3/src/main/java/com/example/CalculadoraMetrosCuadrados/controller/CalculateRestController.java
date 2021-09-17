package com.example.CalculadoraMetrosCuadrados.controller;

import com.example.CalculadoraMetrosCuadrados.dto.*;
import com.example.CalculadoraMetrosCuadrados.exception.NoSuchBarrioException;
import com.example.CalculadoraMetrosCuadrados.service.ICalculateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  private ICalculateService calculateService;// = new CalculateServiceImpl(new BarrioRepository());

  CalculateRestController( ICalculateService calculateService ) {
    this.calculateService = calculateService;
  }

  @PostMapping("/calculateTotalSquareFeet")
  public HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(@RequestBody @Valid HouseDTO house){
    return calculateService.calculateTotalSquareFeet(house);
  }

  @PostMapping("/calculatePrice")
  public ResponseEntity<HouseResponseValueDTO> calculatePrice(@RequestBody @Valid HouseDTO house)
          throws NoSuchBarrioException {
      var dto = calculateService.calculatePrice(house);
      return new ResponseEntity(dto, HttpStatus.OK);
      //return ResponseEntity.ok( dto );
  }

  @PostMapping("/getBiggestRoom")
  public RoomDTO getBiggestRoom(@RequestBody @Valid HouseDTO house){
    return calculateService.getBiggestRoom(house);
  }

  @PostMapping("/calculateSquareFeetPerRoom")
  public HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(@RequestBody @Valid HouseDTO house){
    return calculateService.calculateSquareFeetPerRoom(house);
  }

}
