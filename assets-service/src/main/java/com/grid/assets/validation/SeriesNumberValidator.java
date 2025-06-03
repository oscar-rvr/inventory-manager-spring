package com.grid.assets.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SeriesNumberValidator implements ConstraintValidator<ValidSN, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        return value.matches("^[A-Za-z]{4}\\d{4}$");
    }
}
