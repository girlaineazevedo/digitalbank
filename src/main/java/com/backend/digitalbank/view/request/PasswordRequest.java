package com.backend.digitalbank.view.request;

import com.backend.digitalbank.view.service.validator.annotation.ValidatePassword;

public class PasswordRequest {

	@ValidatePassword
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
