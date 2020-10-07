package com.backend.digitalbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Address {

	public Address() {
	}

	public Address(String zipCode, String avenue, String district, String complement, String city, String state) {
		super();
		this.zipCode = zipCode;
		this.avenue = avenue;
		this.district = district;
		this.complement = complement;
		this.city = city;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "cep")
	private String zipCode;
	
	@Column(name = "rua")
	private String avenue;
	
	@Column(name = "bairro")
	private String district;
	
	@Column(name = "complemento")
	private String complement;
	
	@Column(name = "cidade")
	private String city;
	
	@Column(name = "estado")
	private String state;
	
	@OneToOne
    @JoinColumn(name="dados_pessoais_id", referencedColumnName="id")
	private PersonalData personalData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAvenue() {
		return avenue;
	}

	public void setAvenue(String avenue) {
		this.avenue = avenue;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
}
