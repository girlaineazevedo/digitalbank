package com.backend.digitalbank.view.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface IDocumentService {

	public void addDocument(MultipartFile frente, MultipartFile verso, UUID proposalId);
}
