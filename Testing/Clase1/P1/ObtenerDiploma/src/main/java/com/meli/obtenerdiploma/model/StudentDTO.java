package com.meli.obtenerdiploma.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.obtenerdiploma.validator.UpperCase;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter @Setter

//@GroupSequence({StudentDTO.class,FirstOrder.class,SecondOrder.class})
//para ordenar. le agregas groups= y marcas la ejecucion
public class StudentDTO {
    @Size(min=1, max=50, message = "La longitud del nombre no puede superar los {max} caracteres.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|A-Z|a-z|ñ|á|é|í|ó|ú|Á|É|Í|Ó|Ú]*$", message = "El nombre del alumno debe comenzar con mayúscula.")
    //@UpperCase(groups = SecondOrder.class,message = "El nombre del alumno debe comenzar con mayúscula.")
    //debe estar bien documentado si usamos uno propio
    @NotBlank(message = "El nombre del alumno no puede estar vacío.")
    String studentName;

    String message;

    Double averageScore;

    @NotEmpty(message = "La lista no puede ser vacía.")
    List<@Valid SubjectDTO> subjects;
}
