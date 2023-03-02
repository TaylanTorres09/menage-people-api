package com.manager.people.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.repositories.AddressRepository;
import com.manager.people.repositories.PersonRepository;

@Service
public class StartDBService {
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void instatiateDataBase() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Person person1 = new Person(null, "Nikola Tesla", LocalDate.parse("10/07/1856", fmt));
        Person person2 = new Person(null, "James Gosling", LocalDate.parse("19/05/1955", fmt));

        Address addressP1 = new Address(null, "Smiljan", "60440-134", 5, "condado de Lika", true, person1);
        Address addressP2 = new Address(null, "Calgary", "44078-964", 8, "Alberta", true, person2);

        personRepository.saveAll(Arrays.asList(person1, person2));
        addressRepository.saveAll(Arrays.asList(addressP1, addressP2));

    }

}
