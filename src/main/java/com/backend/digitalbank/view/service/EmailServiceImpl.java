package com.backend.digitalbank.view.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class EmailServiceImpl implements IEmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${email.from}")
	private String emailFrom;
	
	@Override
	@Async
	public void sendEmail(String to, String subject, String text) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailFrom);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		emailSender.send(message);
	}

}
