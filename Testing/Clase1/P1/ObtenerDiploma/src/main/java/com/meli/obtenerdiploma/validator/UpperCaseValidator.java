package com.meli.obtenerdiploma.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpperCaseValidator implements ConstraintValidator<UpperCase, String>{
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return Character.isUpperCase(value.charAt(0));
        }
    }
