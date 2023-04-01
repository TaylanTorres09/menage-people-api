package com.manager.people.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import com.manager.people.dtos.AddressDTO;
import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.services.AddressService;

public class AddressControllerTest {

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
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private Person person = new Person();
    private Address address = new Address();
    private AddressDTO addressDTO = new AddressDTO();

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
    void whenCreateThenReturnStatusCreated() {
        when(addressService.addAddressToPerson(any())).thenReturn(address);

        ResponseEntity<Address> response = addressController.create(addressDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ID, response.getBody().getId());
        assertEquals(street, response.getBody().getStreet());
        assertEquals(cep, response.getBody().getCep());
        assertEquals(numberAddress, response.getBody().getNumberAddress());
        assertEquals(city, response.getBody().getCity());
        assertEquals(principalAddress, response.getBody().getPrincipalAddress());
        assertEquals(person, response.getBody().getPerson());
    }

    private void start() {
        person = new Person(ID, name, LocalDate.parse(birthDate, fmt));
        address = new Address(ID, street, cep, numberAddress, city, principalAddress, person);
        addressDTO = new AddressDTO(street, cep, numberAddress, city, principalAddress, ID);
    }

}
