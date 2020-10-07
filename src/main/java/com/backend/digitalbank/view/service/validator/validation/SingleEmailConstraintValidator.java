package com.backend.digitalbank.view.service.validator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.digitalbank.infrastructure.IPersonalDataRepository;
import com.backend.digitalbank.view.service.validator.annotation.ValidateSingleEmail;

public class SingleEmailConstraintValidator implements ConstraintValidator<ValidateSingleEmail, String> {
	 
	@Autowired
	public IPersonalDataRepository personalDataRepository;
	
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
    	return !personalDataRepository.existsByEmail(email);
    }
}