package com.manager.people.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Person person = new Person(personDTO.getId(), personDTO.getName(), LocalDate.parse(personDTO.getBirthDate(), fmt));
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

    public Person updatPerson(PersonDTO personDTO) {
        Person person = findByID(personDTO.getId());
        
        BeanUtils.copyProperties(personDTO, person);

        return person;
    }

    public Address principalAddress(Long id) {
        List<Address> addresses = addressPerson(id);
        if (!addresses.isEmpty()) {
            List<Address> addressesFilter = addresses.stream().filter(adr -> adr.getPrincipalAddress() == true).toList();

            return addressesFilter.isEmpty() ? null : addressesFilter.get(0);
        }

        return null;
    }

}
