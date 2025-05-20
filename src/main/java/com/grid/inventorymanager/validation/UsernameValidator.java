package com.grid.inventorymanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        // Empieza con letra, solo letras y números, entre 4 y 20 caracteres
        return value.matches("^[A-Za-z][A-Za-z0-9]{3,19}$");
    }
}
