package com.manager.people.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Person;
import com.manager.people.repositories.PersonRepository;

public class PersonServiceTest {

    private static final Long ID = Long.valueOf(1);
    private static final String name = "Ostrogildo";
    private static final String dateBirth = "09/01/1998";

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper mapper;

    private Person person = new Person();
    private PersonDTO personDTO = new PersonDTO();

    @BeforeEach
    void setUp() {
        // Iniciar Mocks da classe
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    private void startPerson() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        person = new Person(ID, name, LocalDate.parse(dateBirth, fmt));
        personDTO = new PersonDTO(ID, name, dateBirth);
    }
}
