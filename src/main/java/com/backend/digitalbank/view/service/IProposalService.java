package com.backend.digitalbank.view.service;

import java.util.UUID;

import com.backend.digitalbank.model.PersonalData;
import com.backend.digitalbank.model.Proposal;

public interface IProposalService {

	public Proposal createProposal(PersonalData personalData);

	public Proposal getProposal(UUID id);

	public String confirmProposal(UUID id, boolean acepted);

	public void isProposalReleased(Proposal proposal);

}
