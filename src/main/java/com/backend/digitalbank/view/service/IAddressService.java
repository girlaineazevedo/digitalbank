package com.backend.digitalbank.view.service;

import java.util.UUID;

import com.backend.digitalbank.model.Address;

public interface IAddressService {

	public void addAddress(Address address, UUID proposalId);
}
