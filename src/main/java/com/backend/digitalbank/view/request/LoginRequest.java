package com.backend.digitalbank.view.request;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

public class LoginRequest {
	
	@Email
	private String email;

	@CPF
	private String cpf;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
