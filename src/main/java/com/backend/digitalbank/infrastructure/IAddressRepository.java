package com.backend.digitalbank.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.digitalbank.model.Address;

public interface IAddressRepository extends JpaRepository<Address, Integer>{

}
