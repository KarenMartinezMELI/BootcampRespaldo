package com.meli.tucasita.dto.district;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@Data
public class DistrictDTO {
    @JsonProperty("district_name")
    @NotBlank(message = "El nombre del barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del nombre del barrio no puede superar los {max} caracteres.")
    String name;
    @JsonProperty("district_built_price")
    @NotNull(message = "El precio del metro cuadrado construido de un barrio no puede estar vacío.")
    @DecimalMax(value = "4000.0",
            message = "El precio máximo permitido por metro cuadrado construido no puede superar los {value} U$S.")
    @DecimalMin(value = "0.0",
            message = "El precio mínimo permitido por metro cuadrado construido no puede ser menor a {value} U$S.")
    Double builtPrice;
    @JsonProperty("district_unbuilt_price")
    @NotNull(message = "El precio del metro cuadrado no construido de un barrio no puede estar vacío.")
    @DecimalMax(value = "1000.0",
            message = "El precio máximo permitido por metro cuadrado no construido no puede superar los {value} U$S.")
    @DecimalMin(value = "0.0",
            message = "El precio mínimo permitido por metro cuadrado no construido no puede ser menor a {value} U$S.")
    Double unbuiltPrice;
}
