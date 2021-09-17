package com.meli.obtenerdiploma.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = UpperCaseValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpperCase {
    String message() default "El nombre del alumno debe comenzar con mayúscula.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
