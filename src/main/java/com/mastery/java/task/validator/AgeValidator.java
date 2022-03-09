package com.mastery.java.task.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    private Integer age;

    @Override
    public void initialize(ValidAge validAge) {
        this.age = validAge.lower();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {

        if (age > Period.between(dateOfBirth, LocalDate.now()).getYears()) return true;

        return false;
    }


}
