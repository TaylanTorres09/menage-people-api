package com.manager.people.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manager.people.dtos.AddressDTO;
import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.services.AddressService;
import com.manager.people.services.PersonService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public PersonDTO findByID(@PathVariable Long id) {
        return personService.findByIdWithOutAddress(id);
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAllPerson();
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = personService.createPerson(personDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(String.format("/person/%d", person.getId())).buildAndExpand(person.getId()).toUri();

        //return uri in headers
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/address/{personId}")
    public List<Address> addressPerson(@PathVariable(name = "personId") Long personId) {
        return personService.addressPerson(personId);
    }

    @PutMapping("/address")
    public ResponseEntity<List<Address>> addAddressPerson(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.addAddressToPerson(addressDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(String.format("/person/address/%d", addressDTO.getPersonId())).buildAndExpand(addressDTO.getPersonId()).toUri();

        //return uri in headers
        return ResponseEntity.noContent().location(uri).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@Valid @RequestBody PersonDTO personDTO) {
        Person person = personService.updatPerson(personDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(String.format("/person/%d", person.getId())).buildAndExpand(person.getId()).toUri();

        //return uri in headers
        return ResponseEntity.noContent().location(uri).build();
    }

    @GetMapping("address/principal/{personId}")
    public Address principalAddress(@PathVariable(name = "personId") Long personId) {
        return personService.principalAddress(personId);
    }


}
