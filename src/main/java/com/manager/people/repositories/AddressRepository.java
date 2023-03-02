package com.manager.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.people.models.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
