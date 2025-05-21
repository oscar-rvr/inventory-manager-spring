package com.grid.inventorymanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if( value == null || value.isEmpty() ){
            return false;
        }

        return value.matches("^[A-Za-z0-9._%+-]+@griddynamics\\.com$");
    }
}
