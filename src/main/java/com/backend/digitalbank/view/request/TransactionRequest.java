package com.backend.digitalbank.view.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class TransactionRequest {

	@NotEmpty
    private String bancoCod;
	
    @NotEmpty
    private String bancoNome;
    
    @Size(max=4, message="não pode ser maior que 4 caracteres")
    @NumberFormat(style = Style.NUMBER)
    @NotEmpty
    private String agencia;
    
    @NotEmpty
    private String conta;
    
    @NotEmpty
    private String nome;
    
    @NotEmpty
    private String documento;
    
    @NotNull
    private boolean favoritar;
    
    @NotEmpty
    private String tipoConta;
    
    @NotEmpty
    private String descricao;
   
    @NotNull
    @DecimalMin("1.00")
    private BigDecimal valor;

	public String getBancoCod() {
		return bancoCod;
	}

	public void setBancoCod(String bancoCod) {
		this.bancoCod = bancoCod;
	}

	public String getBancoNome() {
		return bancoNome;
	}

	public void setBancoNome(String bancoNome) {
		this.bancoNome = bancoNome;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public boolean isFavoritar() {
		return favoritar;
	}

	public void setFavoritar(boolean favoritar) {
		this.favoritar = favoritar;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}