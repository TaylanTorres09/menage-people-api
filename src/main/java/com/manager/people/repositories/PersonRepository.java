package com.manager.people.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.people.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
