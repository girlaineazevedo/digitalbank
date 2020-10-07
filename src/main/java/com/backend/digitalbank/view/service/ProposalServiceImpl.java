package com.backend.digitalbank.view.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.digitalbank.infrastructure.IProposalRepository;
import com.backend.digitalbank.infrastructure.exception.DigitalBankingException;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.infrastructure.exception.UnprocessableEntityException;
import com.backend.digitalbank.model.PersonalData;
import com.backend.digitalbank.model.Proposal;
import com.backend.digitalbank.model.ProposalStatus;

@Service
public class ProposalServiceImpl implements IProposalService {

	@Autowired
	private IProposalRepository proposalRepository;

	@Autowired
	private IPersonalDataService personalDataService;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private IAccountService accountService;

	@Override
	@Transactional
	public Proposal createProposal(PersonalData personalData) {
		
		Proposal proposal = proposalRepository.save(new Proposal());
		personalData.setProposal(proposal);
		personalDataService.createPersonalData(personalData);

		return proposal;
	}

	@Override
	public Proposal getProposal(UUID id) {

		Optional<Proposal> proposal = proposalRepository.findById(id);

		if (!proposalRepository.validateResumeProposal(id))
			throw new UnprocessableEntityException("Proposta não está completa.");

		return proposal.orElseThrow(() -> new EntityNotFoundException("Proposta não encontrada."));
	}

	@Override
	public String confirmProposal(UUID id, boolean acepted) {

		String messageReturn = "";

		if (acepted) {
			accountService.createAccount(id);
			messageReturn = "Obrigado pelas informações. Enviaremos um email assim que a conta for criada.";
		} else {
			Proposal proposal = getProposal(id);
			proposal.registerProposal();
			proposalRepository.save(proposal);

			emailService.sendEmail(proposal.getPersonalData().getMail(), "Digital Bank - Confirme seus dados",
					"Olá, não gostou da proposta? Poderiamos ajudar de alguma forma? Estamos aguardando seu aceite");
		}
		return messageReturn;
	}

	@Override
	public void isProposalReleased(Proposal proposal) {
		if (proposal.getStatus() != ProposalStatus.RELEASED) {
			throw new DigitalBankingException("Proposta ainda não foi aceita!");
		}
	}
}
