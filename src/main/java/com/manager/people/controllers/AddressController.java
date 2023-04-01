package com.manager.people.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.people.dtos.AddressDTO;
import com.manager.people.models.Address;
import com.manager.people.services.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<Address> create(@Valid @RequestBody AddressDTO addressDTO) {
        Address address = addressService.addAddressToPerson(addressDTO);
        return new ResponseEntity<Address>(address, HttpStatus.CREATED);
    }

}
