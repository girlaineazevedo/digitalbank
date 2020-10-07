package com.backend.digitalbank.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.digitalbank.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT u FROM User u WHERE u.token = ?1")
	User findByToken(String token);

	@Query(value = "SELECT u FROM User u WHERE u.personalData.mail = ?1")
	User findByEmailPassword(String email);
}
