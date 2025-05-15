package com.safetynet.alerts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.*;
import java.io.IOException;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;//dependency

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    //get all people
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        personRepository.save(person);
        System.out.println("Person added:" + person);
        return person;
    }

    public boolean updatePerson(String firstName, String lastName, Person updatedPerson) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (person != null) {
            person.setAddress(updatedPerson.getAddress());
            person.setCity(updatedPerson.getCity());
            person.setZip(updatedPerson.getZip());
            person.setPhone(updatedPerson.getPhone());
            person.setEmail(updatedPerson.getEmail());
            return true;
        }
        return false;
    }

    public boolean deletePerson(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (person != null) {
            personRepository.delete(person);
            return true;
        }
        return false;
    }
}
