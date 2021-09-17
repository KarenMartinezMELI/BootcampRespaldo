package com.meli.tucasita.dto.environment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Data
public class EnvironmentResponseSquareFeetDTO {
    @JsonProperty("room_name")
    private String name;
    @JsonProperty("room_total_square_feet")
    private Double totalSquareFeet;
}
