package com.manager.people.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.people.models.Person;
import com.manager.people.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person findByID(@PathVariable Long id) {
        return personService.findByID(id);
    }

}
