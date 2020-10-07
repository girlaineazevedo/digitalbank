package com.backend.digitalbank.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.digitalbank.model.PersonalData;

public interface IPersonalDataRepository extends JpaRepository<PersonalData, Integer> {

	@Query(value = "SELECT CASE WHEN count(pd)>0 THEN true ELSE false END FROM PersonalData pd WHERE pd.document = ?1")
	boolean existsByDocument(String document);

	@Query(value = "SELECT CASE WHEN count(pd)>0 THEN true ELSE false END FROM PersonalData pd WHERE pd.mail = ?1")
	boolean existsByEmail(String mail);

	@Query(value = "SELECT pd FROM PersonalData pd WHERE pd.proposal.id = ?1")
	Optional<PersonalData> findByProposalId(UUID proposalId);

	@Query(value = "SELECT pd FROM PersonalData pd WHERE pd.mail = ?1 AND pd.document = ?2")
	Optional<PersonalData> findByEmailDocument(String mail, String cpf);

	@Query(value = "SELECT pd FROM PersonalData pd WHERE pd.mail = ?1")
	Optional<PersonalData> findByEmail(String mail);
}
