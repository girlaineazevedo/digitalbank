package com.backend.digitalbank.view.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import com.backend.digitalbank.view.service.validator.annotation.ValidateMinAge;
import com.backend.digitalbank.view.service.validator.annotation.ValidateSingleCPF;
import com.backend.digitalbank.view.service.validator.annotation.ValidateSingleEmail;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ProposalRequest {

	@NotEmpty
	private String nome;

	@NotEmpty
	private String sobrenome;

	@Email
	@ValidateSingleEmail
	private String email;

	@CPF
	@ValidateSingleCPF
	private String cpf;

	@NotNull
	@ValidateMinAge
	@Past(message = "Data deve estar no passado")
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
