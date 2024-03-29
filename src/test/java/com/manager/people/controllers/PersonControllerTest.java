package com.manager.people.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.manager.people.dtos.PersonDTO;
import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.services.AddressService;
import com.manager.people.services.PersonService;

public class PersonControllerTest {

    private static final Long ID = Long.valueOf(1);
    private static final String name = "Ostrogildo";
    private static final String birthDate = "09/01/1998";
    private static final String street = "Smiljan";
    private static final String cep = "60440-134";
    private static final Integer numberAddress = 5;
    private static final String city = "condado de Lika";
    private static final Boolean principalAddress = true;

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Mock
    private AddressService addressService;

    private Person person = new Person();
    private PersonDTO personDTO = new PersonDTO();
    private Address address = new Address();

    @BeforeEach
    void setUp() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes attributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(attributes);
        MockitoAnnotations.openMocks(this);
        start();
    }

    @After(value = "")
    void teardown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void whenFindByIDThenReturnStatusOK() {
        when(personService.findByIdWithOutAddress(anyLong())).thenReturn(personDTO);

        PersonDTO response = personController.findByID(ID);

        assertNotNull(response);
        assertEquals(PersonDTO.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(name, response.getName());
        assertEquals(birthDate, response.getBirthDate());
    }

    @Test
    void whenFindAllThenReturnOK() {
        when(personService.findAllPerson()).thenReturn(List.of(personDTO));

        List<PersonDTO> response = personController.findAll();

        assertNotNull(response);

        PersonDTO person1 = response.get(0);
        assertEquals(PersonDTO.class, person1.getClass());
        assertEquals(ID, person1.getId());
        assertEquals(name, person1.getName());
        assertEquals(birthDate, person1.getBirthDate());
    }

    @Test
    void whenCreateThenReturnStatusCreated() {
        when(personService.createPerson(any())).thenReturn(person);

        ResponseEntity<Person> response = personController.createPerson(personDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenListAddressOfPersonThenReturnListOfAddresses() {
        when(personService.addressPerson(anyLong())).thenReturn(List.of(address));

        List<Address> response = personController.addressPerson(ID);

        assertNotNull(response);
        
        Address address1 = response.get(0);

        assertEquals(Address.class, address1.getClass());
        assertEquals(ID, address1.getId());
        assertEquals(street, address1.getStreet());
        assertEquals(cep, address1.getCep());
        assertEquals(numberAddress, address1.getNumberAddress());
        assertEquals(city, address1.getCity());
        assertEquals(principalAddress, address1.getPrincipalAddress());
        assertEquals(person, address1.getPerson());

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(personService.updatePerson(any())).thenReturn(person);

        ResponseEntity<Person> response = personController.updatePerson(personDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        assertNotNull(response.getHeaders().get("Location"));
    }

    private void start() {
        person = new Person(ID, name, LocalDate.parse(birthDate, fmt));
        personDTO = new PersonDTO(ID, name, birthDate);
        address = new Address(ID, street, cep, numberAddress, city, principalAddress, person);
    }

}
