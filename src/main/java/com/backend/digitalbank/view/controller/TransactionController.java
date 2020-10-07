package com.backend.digitalbank.view.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backend.digitalbank.model.AccountType;
import com.backend.digitalbank.model.Transaction;
import com.backend.digitalbank.view.request.TransactionRequest;
import com.backend.digitalbank.view.service.ITransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	private ITransactionService transactionService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public String transfer(
		@RequestHeader HttpHeaders headers,
		@Valid @RequestBody TransactionRequest request) 
	{
		Transaction transacation = new Transaction(
			request.getAgencia(),
			request.getConta(),
			request.getBancoCod(),
			request.getBancoNome(),
			request.getDescricao(),
			request.getNome(),
			request.getDocumento(),
			request.isFavoritar(),
			AccountType.valueOf(request.getTipoConta().toUpperCase()),
			request.getValor()
		);
		return transactionService.excute(transacation, headers.get("Authorization").get(0));
	}
}
