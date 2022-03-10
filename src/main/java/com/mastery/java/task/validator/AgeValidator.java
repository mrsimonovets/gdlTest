package com.mastery.java.task.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    private Integer minimumAge;

    @Override
    public void initialize(ValidAge validAge) {
        this.minimumAge = validAge.lower();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext constraintValidatorContext) {

        if (Period.between(dateOfBirth, LocalDate.now()).getYears() >= minimumAge) return true;

        return false;
    }


}
