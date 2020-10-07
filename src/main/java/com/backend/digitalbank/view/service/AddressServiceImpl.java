package com.backend.digitalbank.view.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.digitalbank.infrastructure.IAddressRepository;
import com.backend.digitalbank.model.Address;
import com.backend.digitalbank.model.PersonalData;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	public IAddressRepository adressRepository;
	
	@Autowired
	private IPersonalDataService personalDataService;
	
	@Override
	public void addAddress(Address address, UUID proposalId) {
		
		PersonalData personalData = personalDataService.findByProposalId(proposalId);
		address.setPersonalData(personalData);
		adressRepository.save(address);
	}

}
