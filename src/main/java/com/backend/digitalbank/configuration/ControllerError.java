package com.backend.digitalbank.configuration;

import java.util.Arrays;
import java.util.List;

public class ControllerError {

    private List<String> errors;
 
    public ControllerError(String error) {
        this.errors = Arrays.asList(error);
    }
    
    public ControllerError(List<String> errors) {
        this.errors = errors;
    }

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
