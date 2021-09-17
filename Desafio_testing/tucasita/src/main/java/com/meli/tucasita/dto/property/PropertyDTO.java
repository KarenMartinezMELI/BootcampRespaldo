package com.meli.tucasita.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.tucasita.dto.environment.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class PropertyDTO {
    @JsonProperty("district_name")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los {max} caracteres.")
    String districtName;
    @JsonProperty("prop_name")
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los {max} caracteres.")
    String name;
    @JsonProperty("prop_land_width")
    @NotNull(message = "El ancho del terreno no puede estar vacío.")
    @DecimalMax(value = "75.0",message = "El máximo ancho permitido por terreno es de {value} mts.")
    @DecimalMin(value = "0.1",message = "El mínimo ancho permitido por terreno es de {value} mts.")
    Double width;
    @JsonProperty("prop_land_length")
    @NotNull(message = "El largo del terreno no puede estar vacío.")
    @DecimalMax(value = "100.0",message = "El máximo largo permitido por terreno es de {value} mts.")
    @DecimalMin(value = "0.1",message = "El mínimo largo permitido por terreno es de {value} mts.")
    Double length;
    @JsonProperty("rooms")
    @NotEmpty(message = "La lista de ambientes no puede ser vacía.")
    List<@Valid EnvironmentDTO> environments;
}
