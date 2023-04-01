package com.manager.people.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.manager.people.dtos.AddressDTO;
import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.repositories.AddressRepository;

public class AddressServiceTest {

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
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ModelMapper mapper;

    private Person person = new Person();
    private Address address = new Address();
    private AddressDTO addressDTO = new AddressDTO();

    @BeforeEach
    void setUp() {
        // Iniciar Mocks da classe
        MockitoAnnotations.openMocks(this);
        startAddress();
    }

    private void startAddress() {
        person = new Person(ID, name, LocalDate.parse(birthDate, fmt));
        address = new Address(ID, street, cep, numberAddress, city, principalAddress, person);
        addressDTO = new AddressDTO(street, cep, numberAddress, city, principalAddress, ID);
    }

}
