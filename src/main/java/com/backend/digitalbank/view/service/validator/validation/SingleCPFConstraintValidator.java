package com.backend.digitalbank.view.service.validator.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.digitalbank.infrastructure.IPersonalDataRepository;
import com.backend.digitalbank.view.service.validator.annotation.ValidateSingleCPF;

public class SingleCPFConstraintValidator implements ConstraintValidator<ValidateSingleCPF, String> {
	 
	@Autowired
	public IPersonalDataRepository personalDataRepository;
	
    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
    	return !personalDataRepository.existsByDocument(cpf);
    }
}