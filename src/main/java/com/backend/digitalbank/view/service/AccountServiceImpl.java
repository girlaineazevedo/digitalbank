package com.backend.digitalbank.view.service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.backend.digitalbank.infrastructure.IAccountRepository;
import com.backend.digitalbank.infrastructure.IProposalRepository;
import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.model.Account;
import com.backend.digitalbank.model.Proposal;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IProposalRepository proposalRepository;

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IEmailService emailService;

	@Override
	@Async
	public void createAccount(UUID id) {

		String document = changeProposalStatusToWait(id);

		Future<Boolean> completableFuture = validateDocument(document);

		try {
			if (completableFuture.get().booleanValue()) {
				effectProposal(id);
			}
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new DigitalBankingException("Erro de comunicação com sistema externo.");
		}
	}
	
	private String changeProposalStatusToWait(UUID id) {
		Optional<Proposal> opProposal = proposalRepository.findById(id);
		Proposal proposal = opProposal.orElseThrow(() -> new EntityNotFoundException("Proposta não encontrada."));

		proposal.waitProposal();
		proposalRepository.save(proposal);
		
		return proposal.getPersonalData().getDocument();
	}

	private void effectProposal(UUID id) {
		Proposal proposal = proposalRepository.findById(id).get();
		
		Account account = new Account();
		account.setAgency(genarateRandomAgency());
		account.setAccountNumber(genarateRandomAccount());
		account.setProposal(proposal);

		accountRepository.save(account);
		
		proposal.effectProposal();
		proposalRepository.save(proposal);
		
		emailService.sendEmail(proposal.getPersonalData().getMail(), "Digital Bank - Conta criada",
				"Sua Conta foi criada! \n" + "Agência: " + account.getAgency() + "\n" + "Conta: "
						+ account.getAccountNumber() + "\n" + "Banco: " + account.getBank());
	}
	
	private String genarateRandomAgency() {
		Random rand = new Random();
		return String.format("%04d", rand.nextInt(10000));
	}

	private String genarateRandomAccount() {
		Random rand = new Random();
		return String.format("%08d", rand.nextInt(100000000));
	}

	@Recover
	private void recover(Exception ex, UUID id) {
		Proposal proposal = proposalRepository.findById(id).get();
		proposal.waitProposal();
		proposalRepository.save(proposal);
	}

	/**
	 * Simulate External System for teste
	 * 
	 * @param document
	 * @return
	 * @throws InterruptedException
	 */
	@Retryable(value = Exception.class, maxAttemptsExpression = "${retry.maxAttempts}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	private Future<Boolean> validateDocument(String document) {
		CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
		Executors.newCachedThreadPool().submit(() -> {
			Thread.sleep(10000);
			completableFuture.complete(true);
			return null;
		});

		return completableFuture;
	}
}
