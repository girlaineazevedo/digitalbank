package com.backend.digitalbank.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dados_pessoais")
public class PersonalData {

	public PersonalData(String name, String lastName, String mail, String document, LocalDate birthDate) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.mail = mail;
		this.document = document;
		this.birthDate = birthDate;
	}
	
	public PersonalData() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "nome")
	private String name;
	
	@Column(name = "sobrenome")
	private String lastName;
	
	@Column(name = "email")
	private String mail;
	
	@Column(name = "cpf")
	private String document;
	
	@Column(name = "data_nascimento")
	private LocalDate birthDate;
	
	@OneToOne
    @JoinColumn(name="proposta_id", referencedColumnName="id")
	private Proposal proposal;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "personalData")
	private Address address;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "personalData")
	private Document documentPhoto;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "personalData")
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "personalData")
	private List<Favorite> favorites;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Document getDocumentPhoto() {
		return documentPhoto;
	}

	public void setDocumentPhoto(Document documentPhoto) {
		this.documentPhoto = documentPhoto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
