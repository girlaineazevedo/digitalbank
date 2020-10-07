package com.backend.digitalbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorito")
public class Favorite{
    
	public Favorite(String name, String document, String accountNumber, String agency, String bankCode,
			PersonalData personalData) {
		super();
		this.name = name;
		this.document = document;
		this.accountNumber = accountNumber;
		this.agency = agency;
		this.bankCode = bankCode;
		this.personalData = personalData;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String name;

    @Column(name = "cpf")
    private String document;
    
    @Column(name = "conta")
    private String accountNumber;

    @Column(name = "agencia")
    private String agency;

    @Column(name = "codigo_banco")
    private String bankCode;

	@ManyToOne
    @JoinColumn(name="dados_pessoais_id", referencedColumnName="id")
    private PersonalData personalData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

}