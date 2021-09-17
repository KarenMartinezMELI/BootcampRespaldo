package com.example.CalculadoraMetrosCuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
public class HouseResponseValueDTO {
  private Double value;
  private Double pricePeerSquadMeter;
  private String name;
}
