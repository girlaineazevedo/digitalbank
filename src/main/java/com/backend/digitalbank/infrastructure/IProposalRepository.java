package com.backend.digitalbank.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.digitalbank.model.Proposal;

public interface IProposalRepository extends JpaRepository<Proposal, UUID> {

	@Query(value = "SELECT CASE WHEN count(p)>0 THEN true ELSE false END FROM Proposal p "
			+ "INNER JOIN PersonalData dp ON dp.proposal.id = p.id "
			+ "INNER JOIN Address e ON e.personalData.id = dp.id WHERE p.id = ?1")
	boolean validateProposal(UUID proposalId);
	
	@Query(value = "SELECT CASE WHEN count(p)>0 THEN true ELSE false END FROM Proposal p "
			+ "INNER JOIN PersonalData dp ON dp.proposal.id = p.id "
			+ "INNER JOIN Address e ON e.personalData.id = dp.id "
			+ "INNER JOIN Document d ON d.personalData.id = dp.id  WHERE p.id = ?1")
	boolean validateResumeProposal(UUID proposalId);
}
