package com.meli.obtenerdiploma.model;

import com.meli.obtenerdiploma.validator.UpperCase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Getter @Setter

//@GroupSequence({SubjectDTO.class,FirstOrder.class,SecondOrder.class})
public class SubjectDTO {
    @NotBlank(message = "El nombre de la materia no puede estar vacío.")
    @Size(min=1, max=30, message = "La longitud del nombre no puede superar los {max} caracteres.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|A-Z|a-z|ñ|á|é|í|ó|ú|Á|É|Í|Ó|Ú]*$",message = "El nombre de la materia debe comenzar con mayúscula.")
    //@UpperCase(groups = SecondOrder.class ,message = "El nombre de la materia debe comenzar con mayúscula.")
    String name;

    @NotNull(message = "La nota no puede estar vacía.")
    @DecimalMin(value = "0.0", message = "La mínima nota es {value}")
    @DecimalMax(value = "10.0", message = "La máxima nota es {value}")
    Double score;
}
