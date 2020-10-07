package com.backend.digitalbank.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.digitalbank.model.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer>{

	@Query(value = "SELECT a FROM Account a WHERE a.proposal.id = ?1")
	Account getByProposalId(UUID proposalId);
}
