package com.manager.people.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Person;
import com.manager.people.repositories.PersonRepository;
import com.manager.people.services.exceptions.ObjectNotFound;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper mapper;

    public Person findByID(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Person not found"));
    }

    public PersonDTO findByIdWithOutAddress(Long id) {
        Person person = this.findByID(id);
        return mapper.map(person, PersonDTO.class);
    }

}
