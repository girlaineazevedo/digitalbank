package com.backend.digitalbank.view.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class AuthenticationRequest{
	
    @Email
    private String email;
    
    @NotEmpty
    private String senha;

    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}