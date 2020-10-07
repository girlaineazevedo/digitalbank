package com.backend.digitalbank.view.service;

public interface ILoginService {

	public void createToken(String mail, String cpf);

	public void createPassword(String senha, String token);

	public String authenticate(String user, String password);
}
