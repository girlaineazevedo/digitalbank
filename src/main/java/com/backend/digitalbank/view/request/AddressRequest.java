package com.backend.digitalbank.view.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class AddressRequest {

	@NotEmpty
	@Pattern(regexp = "(^\\d{5}-\\d{3}$)", message = "CEP deve ter o formato '00000-000'")
	private String cep;

	@NotEmpty
	private String rua;
	
	@NotEmpty
	private String bairro;
	
	@NotEmpty
	private String complemento;
	
	@NotEmpty
	private String cidade;
	
	@NotEmpty
	private String estado;

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
}
