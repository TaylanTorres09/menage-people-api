package com.manager.people.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Address;
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

    public Person createPerson(PersonDTO personDTO) {
        Person person = mapper.map(personDTO, Person.class);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person.setBirthDate(LocalDate.parse(personDTO.getBirthDate(), fmt));
        return savePerson(person);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public List<PersonDTO> findAllPerson() {
        List<Person> people = personRepository.findAll();
        List<PersonDTO> personDTOs = people.stream().map(person -> mapper.map(person, PersonDTO.class)).toList();

        return personDTOs;
    }
    
    public List<Address> addressPerson(Long id) {
        Person person = this.findByID(id);
        return person.getAddresses();
    }

}
