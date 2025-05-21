package com.grid.inventorymanager.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import lombok.Data;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "Invalid email. Does not belong to griddynamics domain";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
