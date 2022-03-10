package com.mastery.java.task.validator;

import javax.validation.Constraint;
import javax.validation.Valid;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {

    String message() default "Age should be more than 18";

    int lower() default 18;

    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
