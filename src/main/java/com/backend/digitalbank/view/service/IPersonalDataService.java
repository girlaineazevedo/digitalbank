package com.backend.digitalbank.view.service;

import java.util.UUID;

import com.backend.digitalbank.model.PersonalData;

public interface IPersonalDataService {

	public void createPersonalData(PersonalData personalData);

	public PersonalData findByProposalId(UUID proposalId);
}
