package com.backend.digitalbank.view.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class EmailFakeServiceImpl implements IEmailService{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	@Async
	public void sendEmail(String to, String subject, String text) {
		
		logger.info(String.format("Email %s para: %s: %s Não enviado em abiente dev", subject, to, text));
	}

}
