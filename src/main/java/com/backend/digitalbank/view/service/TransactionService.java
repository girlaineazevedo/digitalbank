package com.backend.digitalbank.view.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.digitalbank.infrastructure.IAccountRepository;
import com.backend.digitalbank.infrastructure.IFavoriteRepository;
import com.backend.digitalbank.infrastructure.IPersonalDataRepository;
import com.backend.digitalbank.infrastructure.ITransactionRepository;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.infrastructure.security.AuthenticationUtils;
import com.backend.digitalbank.model.Account;
import com.backend.digitalbank.model.Favorite;
import com.backend.digitalbank.model.PersonalData;
import com.backend.digitalbank.model.Transaction;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	private ITransactionRepository transactionRepository;

	@Autowired
	private IFavoriteRepository favoriteRepository;

	@Autowired
	private IPersonalDataRepository personalDataRepository;

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private AuthenticationUtils authenticationUtils;

	@Autowired
	private IEmailService emailService;

	private static final int LIMIT_HOUR = 17;

	@Override
	public String excute(Transaction transaction, String token) {
		PersonalData personalData = getPersonalDataByToken(token);
		Account myAccount = accountRepository.getByProposalId(personalData.getProposal().getId());

		if (LocalDateTime.now().getHour() > LIMIT_HOUR) {
			transaction.setMyAccount(myAccount);
			transaction.scheduler();

			transactionRepository.save(transaction);

			emailService.sendEmail(personalData.getMail(), "Digital Bank - Transferencia agendada",
					"Transação fora do horário. Transferência será agendada para o próximo dia útil");
		} else {

			myAccount.withdraw(transaction.getAmount());

			transaction.setMyAccount(myAccount);
			transaction.done();
			transactionRepository.save(transaction);

			accountRepository.save(myAccount);

			if (transaction.isSaveToFavorites())
				favor(personalData, transaction);

		}
		return "Transação realizada com sucesso!";
	}

	public PersonalData getPersonalDataByToken(String token) {
		String email = authenticationUtils.getSubjectToken(token);
		Optional<PersonalData> opPersonal = personalDataRepository.findByEmail(email);

		return opPersonal.orElseThrow(() -> new EntityNotFoundException("Usuário não encontado"));
	}

	public void favor(PersonalData personalData, Transaction transaction) {

		Favorite favorite = new Favorite(transaction.getName(), transaction.getDocument(), transaction.getAccount(),
				transaction.getAgency(), transaction.getCodBank(), personalData);

		favoriteRepository.save(favorite);
	}
}