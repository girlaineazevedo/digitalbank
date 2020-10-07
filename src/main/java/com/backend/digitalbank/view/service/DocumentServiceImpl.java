package com.backend.digitalbank.view.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.digitalbank.infrastructure.IDocumentRepository;
import com.backend.digitalbank.infrastructure.IProposalRepository;
import com.backend.digitalbank.infrastructure.exception.DocumentException;
import com.backend.digitalbank.infrastructure.exception.UnprocessableEntityException;
import com.backend.digitalbank.model.Document;
import com.backend.digitalbank.model.PersonalData;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	public IDocumentRepository documentRepository;
	
	@Autowired
	private IPersonalDataService personalDataService;
	
	@Autowired
	private IProposalRepository proposalRepository;
	
	
	@Override
	public void addDocument(MultipartFile frente, MultipartFile verso, UUID proposalId){
		
		try {
			PersonalData personalData = personalDataService.findByProposalId(proposalId);
			
			if( frente.isEmpty() || verso.isEmpty() )
				throw new DocumentException( "Documentos frente e verso são obrigatórios." );
			
			if(!proposalRepository.validateProposal(proposalId))
				throw new UnprocessableEntityException("Proposta não está completa.");
			
			Document document = new Document();
			document.setPersonalData(personalData);
			document.setFileFront(frente.getBytes());
			document.setFileVerse(verso.getBytes());
			
			documentRepository.save(document);
		}
		catch(IOException e) {
			throw new DocumentException("Falha no upload do documento.");
		}
	}

}
