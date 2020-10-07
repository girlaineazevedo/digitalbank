package com.backend.digitalbank.view.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.digitalbank.view.request.AuthenticationRequest;
import com.backend.digitalbank.view.request.LoginRequest;
import com.backend.digitalbank.view.request.PasswordRequest;
import com.backend.digitalbank.view.service.ILoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ILoginService loginService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void createToken(@Valid @RequestBody LoginRequest request) {
		loginService.createToken(request.getEmail(), request.getCpf());
	}
	
	@PostMapping("/{token}")
	@ResponseStatus(code = HttpStatus.OK)
	public void createPassword(
			@PathVariable(required = true, name = "token") String token,
			@Valid @RequestBody PasswordRequest senha) {
		loginService.createPassword(senha.getSenha(), token);
	}

	@PostMapping("/authentication")
	@ResponseStatus(code = HttpStatus.OK)
	public String authenticate(
			@Valid @RequestBody AuthenticationRequest login) {
		return loginService.authenticate(login.getEmail(),login.getSenha());
	}
}
