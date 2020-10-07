package com.backend.digitalbank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Lob
	@Column(name = "arquivo_frente")
	private byte[] fileFront;
	
	@Lob
	@Column(name = "arquivo_verso")
	private byte[] fileVerse;
	
	@OneToOne
    @JoinColumn(name="dados_pessoais_id", referencedColumnName="id")
	private PersonalData personalData;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public byte[] getFileFront() {
		return fileFront;
	}

	public void setFileFront(byte[] fileFront) {
		this.fileFront = fileFront;
	}

	public byte[] getFileVerse() {
		return fileVerse;
	}

	public void setFileVerse(byte[] fileVerse) {
		this.fileVerse = fileVerse;
	}
}
