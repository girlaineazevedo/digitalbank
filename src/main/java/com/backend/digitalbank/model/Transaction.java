package com.backend.digitalbank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transacao")
public class Transaction{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "banco_cod")
    private String codBank;

    @Column(name = "banco_nome")
    private String bankName;

    @Column(name = "agencia")
    private String agency;

    @Column(name = "conta")
    private String account;

    @Column(name = "nome")
    private String name;

    @Column(name = "documento")
    private String document;

    @Column(name = "favoritar")
    private boolean saveToFavorites;

    @Column(name = "tipo_conta")
	@Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "status")
	@Enumerated(EnumType.STRING)
    private TransactionStatus status = TransactionStatus.IN_PROGRESS;
    
    @Column(name = "descricao")
    private String description;

    @Column(name = "valor")
    private BigDecimal amount;
	
	@ManyToOne
    @JoinColumn(name="account_id", referencedColumnName="id")
	private Account myAccount;

	public Transaction()
	{
	}
	
	public Transaction(
			String agency,
			String account,
			String codBank,
			String bankName,
			String description,
			String name,
			String document, boolean isFavorite,
			AccountType accountType,
			BigDecimal amount
		){
			this.agency = agency;
			this.account = account;
			this.codBank = codBank;
			this.bankName = bankName;
			this.description = description;
			this.name = name;
			this.document = document;
			this.saveToFavorites = isFavorite;
			this.accountType = accountType;
			this.amount = amount;
		}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodBank() {
		return codBank;
	}

	public void setCodBank(String codBank) {
		this.codBank = codBank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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

	public boolean isSaveToFavorites() {
		return saveToFavorites;
	}

	public void setSaveToFavorites(boolean saveToFavorites) {
		this.saveToFavorites = saveToFavorites;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Account getMyAccount() {
		return myAccount;
	}

	public void setMyAccount(Account myAccount) {
		this.myAccount = myAccount;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	private void setStatus(TransactionStatus status) {
		this.status = status;
	}
	
	public void scheduler() {
		setStatus(TransactionStatus.SCHEDULED);
	}
	
	public void done() {
		setStatus(TransactionStatus.DONE);
	}

}
