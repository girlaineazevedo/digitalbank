package com.backend.digitalbank.view.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.digitalbank.infrastructure.IPersonalDataRepository;
import com.backend.digitalbank.infrastructure.exception.EntityNotFoundException;
import com.backend.digitalbank.model.PersonalData;

@Service
public class PersonalDatalServiceImpl implements IPersonalDataService {

	@Autowired
	public IPersonalDataRepository personalDataRepository;

	@Override
	public void createPersonalData(PersonalData personalData) {
		personalDataRepository.save(personalData);
	}

	@Override
	public PersonalData findByProposalId(UUID proposalId) {
		Optional<PersonalData> personalData = personalDataRepository.findByProposalId(proposalId);
		return personalData.orElseThrow(() -> new EntityNotFoundException("Proposta não encontrada."));
	}
}
