package com.manager.people.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Person;
import com.manager.people.repositories.PersonRepository;
import com.manager.people.services.exceptions.ObjectNotFound;

public class PersonServiceTest {

    private static final Long ID = Long.valueOf(1);
    private static final String name = "Ostrogildo";
    private static final String birthDate = "09/01/1998";

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper mapper;

    private Person person = new Person();
    private PersonDTO personDTO = new PersonDTO();
    
    private Optional<Person> optionalPerson;

    @BeforeEach
    void setUp() {
        // Iniciar Mocks da classe
        MockitoAnnotations.openMocks(this);
        startPerson();
    }

    @Test
    void whenFindByIDThenReturnAPersonInstance() {
        when(personRepository.findById(anyLong())).thenReturn(optionalPerson);

        Person response = personService.findByID(ID);

        assertNotNull(response);
        assertEquals(Person.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(LocalDate.parse(birthDate, fmt), response.getBirthDate());

    }

    @Test
    void whenFindByIDThenReturnAnObjectNotFoundException() {
        when(personRepository.findById(anyLong())).thenThrow(new ObjectNotFound("Person not found"));

        try {
            personService.findByID(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFound.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }
    }

    @Test
    void whenFindByIDThenReturnPersonDTO() {
        when(personRepository.findById(anyLong())).thenReturn(optionalPerson);
        when(mapper.map(any(), any())).thenReturn(personDTO);

        PersonDTO response = personService.findByIdWithOutAddress(ID);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(birthDate, response.getBirthDate());
    }

    @Test
    void whenFindByIDPersonDTOThenThrowObjectNotFound() {
        when(personRepository.findById(ID)).thenThrow(new ObjectNotFound("Person not found"));

        try {
            personService.findByIdWithOutAddress(ID);
        } catch (Exception e) {
            assertEquals(ObjectNotFound.class, e.getClass());
            assertEquals("Person not found", e.getMessage());
        }
    }

    private void startPerson() {
        person = new Person(ID, name, LocalDate.parse(birthDate, fmt));
        personDTO = new PersonDTO(ID, name, birthDate);
        optionalPerson = Optional.of(new Person(ID, name, LocalDate.parse(birthDate, fmt)));
    }
}
