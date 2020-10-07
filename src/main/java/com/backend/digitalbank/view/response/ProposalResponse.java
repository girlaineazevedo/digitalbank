package com.backend.digitalbank.view.response;

import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

import com.backend.digitalbank.model.Proposal;

public class ProposalResponse {
	
	private UUID id;
	private String nome;
	private String sobrenome;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String cep;
	private String rua;
	private String bairro;
	private String complemento;
	private String cidade;
	private String estado;
	private byte[] cpfFrente;
	private byte[] cpfVerso;
	
	public ProposalResponse() {
	}
	
	public ProposalResponse(Proposal proposal) {
		super();
		this.id = proposal.getId();
		this.nome = proposal.getPersonalData().getName();
		this.sobrenome = proposal.getPersonalData().getLastName();
		this.email = proposal.getPersonalData().getMail();
		this.cpf = proposal.getPersonalData().getDocument();
		this.dataNascimento = proposal.getPersonalData().getBirthDate();
		this.cep = proposal.getPersonalData().getAddress().getZipCode();
		this.rua = proposal.getPersonalData().getAddress().getAvenue();
		this.bairro = proposal.getPersonalData().getAddress().getDistrict();
		this.complemento = proposal.getPersonalData().getAddress().getComplement();
		this.cidade = proposal.getPersonalData().getAddress().getCity();
		this.estado = proposal.getPersonalData().getAddress().getState();
		cpfFrente = Base64.getEncoder().encode( proposal.getPersonalData().getDocumentPhoto().getFileFront());
		cpfVerso = Base64.getEncoder().encode( proposal.getPersonalData().getDocumentPhoto().getFileVerse());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte[] getCpfFrente() {
		return cpfFrente;
	}

	public void setCpfFrente(byte[] cpfFrente) {
		this.cpfFrente = cpfFrente;
	}

	public byte[] getCpfVerso() {
		return cpfVerso;
	}

	public void setCpfVerso(byte[] cpfVerso) {
		this.cpfVerso = cpfVerso;
	}

}
