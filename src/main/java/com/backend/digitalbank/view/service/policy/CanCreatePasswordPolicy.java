package com.backend.digitalbank.view.service.policy;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.model.User;

@Component
public class CanCreatePasswordPolicy {

	public void verifyToken(User user){
		
		if (user == null) 
			throw new EntityNotFoundException("Token não encontrado!");
		
		if (LocalDateTime.now().isAfter(user.getExpirationDate()))
			throw new DigitalBankingException("Token expirado!");
		
		if (user.getPassword() != null)
			throw new DigitalBankingException("Token já utilizado!");
	}
}