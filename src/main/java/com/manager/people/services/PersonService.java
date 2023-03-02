package com.manager.people.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Person;
import com.manager.people.repositories.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    public Person findByID(Long id) {
        return personRepository.findById(id).get();
    }

}
