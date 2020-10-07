package com.backend.digitalbank.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "proposta")
public class Proposal {

	public Proposal(){
		setCreatedAt( LocalDateTime.now());
		setUpdatedAt( LocalDateTime.now());
	}
	
	@Id
	@Type(type="uuid-char")
	private UUID id = UUID.randomUUID();
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProposalStatus status = ProposalStatus.IN_PROGRESS;
	
	@Column(name = "data_inclusao")
	private LocalDateTime createdAt;
	
	@Column(name = "data_alteracao")
	private LocalDateTime updatedAt;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "proposal")
	private PersonalData personalData;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "proposal")
	private Account account;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public ProposalStatus getStatus() {
		return status;
	}
	private void setStatus(ProposalStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void registerProposal() {
		setUpdatedAt(LocalDateTime.now());
		setStatus(ProposalStatus.REGISTERED);
	}
	
	public void effectProposal() {
		setUpdatedAt(LocalDateTime.now());
		setStatus(ProposalStatus.RELEASED);
	}
	
	public void waitProposal() {
		setUpdatedAt(LocalDateTime.now());
		setStatus(ProposalStatus.AWAIT_EXTERNAL);
	}
	
	public PersonalData getPersonalData() {
		return personalData;
	}
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
