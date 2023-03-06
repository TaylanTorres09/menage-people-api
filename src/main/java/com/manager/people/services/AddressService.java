package com.manager.people.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.people.dtos.AddressDTO;
import com.manager.people.models.Address;
import com.manager.people.models.Person;
import com.manager.people.repositories.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper mapper;

    public List<Address> addAddressToPerson(AddressDTO addressDTO) {

        Person person = personService.findByID(addressDTO.getPersonId());

        Address address = mapper.map(addressDTO, Address.class);
        address.setId(null);
        address.setPerson(person);
        
        addressRepository.save(address);

        return person.getAddresses();

    }

}
