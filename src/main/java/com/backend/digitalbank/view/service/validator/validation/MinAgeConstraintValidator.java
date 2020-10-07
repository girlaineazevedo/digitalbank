package com.backend.digitalbank.view.service.validator.validation;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.backend.digitalbank.view.service.validator.annotation.ValidateMinAge;

public class MinAgeConstraintValidator implements ConstraintValidator<ValidateMinAge, LocalDate> {

	private static final int MIN_AGE = 18;
	
	
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
    	return !birthDate.isAfter(LocalDate.now().minusYears(MIN_AGE));
    }
}