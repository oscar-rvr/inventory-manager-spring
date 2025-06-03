package com.grid.users.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername { // aqui se define el nombre real de la anotación, osea la que se usa en DTO, asi como el mensaje de error

    String message() default "Username must start with a letter and contain only letters and numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
