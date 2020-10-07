package com.backend.digitalbank.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.digitalbank.model.Document;

public interface IDocumentRepository extends JpaRepository<Document, Integer>{

}
