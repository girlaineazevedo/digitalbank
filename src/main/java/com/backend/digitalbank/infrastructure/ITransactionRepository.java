package com.backend.digitalbank.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.digitalbank.model.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{

}