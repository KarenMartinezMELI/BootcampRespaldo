package com.meli.tucasita.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.dto.environment.EnvironmentResponseSquareFeetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Data
public class PropertyResponseEnvironmentsSquareFeetDTO {

    @JsonProperty("prop_name")
    String name;
    List<EnvironmentResponseSquareFeetDTO> rooms;

}
