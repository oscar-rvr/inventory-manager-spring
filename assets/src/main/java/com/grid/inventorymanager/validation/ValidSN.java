package com.grid.inventorymanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SeriesNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSN {

    String message() default "Invalid Series Number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
