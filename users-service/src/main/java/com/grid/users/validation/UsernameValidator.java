package com.grid.users.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        // Empieza con letra, solo letras y numeros, entre 4 y 20 caracteres
        return value.matches("^[A-Za-z][A-Za-z0-9]{3,19}$");
    }
}
