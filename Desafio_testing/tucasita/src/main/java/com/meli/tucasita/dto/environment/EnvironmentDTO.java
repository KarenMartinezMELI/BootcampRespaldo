package com.meli.tucasita.dto.environment;

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
public class EnvironmentDTO {
    @JsonProperty("room_name")
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los {max} caracteres.")
    String name;
    @JsonProperty("room_width")
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(value = "25.0",
            message = "El máximo ancho permitido por propiedad es de {value} mts.")
    @DecimalMin(value = "0.1",
            message = "El mínimo ancho permitido por propiedad es de {value} mts.")
    Double width;
    @JsonProperty("room_length")
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(value = "33.0",
            message = "El máximo largo permitido por propiedad es de {value} mts.")
    @DecimalMin(value = "0.1",
            message = "El mínimo largo permitido por propiedad es de {value} mts.")
    Double length;
}
