package com.backend.digitalbank.model;

import java.math.BigDecimal;
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

import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;

@Entity
@Table(name = "conta")
public class Account {

	public static final String BANK_NUMBER = "123";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name = "agencia")
	private String agency;
	
	@Column(name = "conta")
	private String accountNumber;
	
	@Column(name = "banco")
	private String bank = BANK_NUMBER;
	
	@Column(name = "saldo")
	private Float balance = 0F;
	
	@OneToOne
    @JoinColumn(name="proposta_id", referencedColumnName="id")
	private Proposal proposal;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<Transaction> transactions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBank() {
		return bank;
	}

	public Float getBalance() {
		return balance;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public void withdraw(BigDecimal amount) {
		if (this.balance < amount.floatValue()) {
			throw new DigitalBankingException("Saldo insuficiente!");
		}
		this.balance -= amount.floatValue();
	}
}
