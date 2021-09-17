package com.meli.tucasita.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class PropertyResponseSquareFeetDTO {
  @JsonProperty("prop_name")
  private String name;
  @JsonProperty("prop_total_square_feet")
  private double totalSquareFeet;

}
