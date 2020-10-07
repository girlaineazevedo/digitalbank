package com.backend.digitalbank.view.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.digitalbank.model.Address;
import com.backend.digitalbank.model.PersonalData;
import com.backend.digitalbank.model.Proposal;
import com.backend.digitalbank.view.request.AddressRequest;
import com.backend.digitalbank.view.request.ProposalRequest;
import com.backend.digitalbank.view.response.ProposalIdResponse;
import com.backend.digitalbank.view.response.ProposalResponse;
import com.backend.digitalbank.view.service.IAddressService;
import com.backend.digitalbank.view.service.IDocumentService;
import com.backend.digitalbank.view.service.IProposalService;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

	@Autowired
	private IProposalService proposalService;
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IDocumentService documentService;

	private HttpHeaders headers = new HttpHeaders();

	@PostMapping()
	public ResponseEntity<ProposalIdResponse> createProposal(@Valid @RequestBody ProposalRequest request)
			throws URISyntaxException {
		
		Proposal proposal = proposalService.createProposal(new PersonalData(request.getNome(), request.getSobrenome(),
				request.getEmail(), request.getCpf(), request.getDataNascimento()));

		ProposalIdResponse response = new ProposalIdResponse();
		response.setId(proposal.getId());

		this.headers.setLocation(new URI("/address"));

		return new ResponseEntity<>(response, this.headers, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/{id}/address")
	public ResponseEntity<ProposalIdResponse> addAddress(
			@Valid @RequestBody AddressRequest request, 
			@PathVariable(required = true, name = "id") UUID id)
			throws URISyntaxException{
		
		addressService.addAddress(new Address(request.getCep(), request.getRua(),
				request.getBairro(), request.getComplemento(), request.getCidade(), request.getEstado()), id);

		ProposalIdResponse response = new ProposalIdResponse();
		response.setId(id);

		this.headers.setLocation(new URI("/document"));

		return new ResponseEntity<>(response, this.headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}/document")
	public ResponseEntity<ProposalIdResponse> addDocument(
			@RequestParam(value = "frente") MultipartFile frente, 
			@RequestParam(value = "verso") MultipartFile verso, 
			@PathVariable(required = true, name = "id") UUID id) throws URISyntaxException{
		
		documentService.addDocument(frente, verso, id);

		ProposalIdResponse response = new ProposalIdResponse();
		response.setId(id);

		this.headers.setLocation(new URI("/resume"));

		return new ResponseEntity<>(response, this.headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProposalResponse> getProposal(@PathVariable(required = true, name = "id") 
			UUID id){
		
		Proposal proposal = proposalService.getProposal(id);
		
		ProposalResponse response = new ProposalResponse(proposal);

		return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/confirm")
	public ResponseEntity<String> confirmProposal(
			@PathVariable(required = true, name = "id") UUID id,
	        @Valid @RequestParam("acepted") boolean acepted ){
		
		String message = proposalService.confirmProposal(id, acepted);

		return new ResponseEntity<>(message, this.headers, HttpStatus.OK);
	}
}
