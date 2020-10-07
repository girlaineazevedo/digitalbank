package com.backend.digitalbank.view.service;

public interface IEmailService {

	public void sendEmail(String to, String subject, String message);
}
