package com.backend.digitalbank.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.infrastructure.exception.UnprocessableEntityException;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		
		List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
		
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	final String error = "Método " + ex.getMethod() + " não suportado para este caminho";
    	return handleExceptionInternal(ex, error, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final String error = "O corpo da requisição não pode ser nulo";
		return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	final String error = "Valor inválido: " + ex.getValue() + ". Deve ser do tipo: " + ex.getRequiredType();
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	final String error = ex.getParameterName() + " é obrigatório.";
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(DigitalBankingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ControllerError handlePersonalException(DigitalBankingException ex) {
		return new ControllerError(ex.getMessage());
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ControllerError handleNotFoundException(EntityNotFoundException ex) {
		return new ControllerError(ex.getMessage());
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ControllerError handleNotFoundException(UnprocessableEntityException ex) {
		return new ControllerError(ex.getMessage());
	}
}
