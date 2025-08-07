package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/person")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        super();
        this.personService = personService;
    }

    @GetMapping("/all")
    public List<Person> getPersonList() {
        log.info("Http Get request received at/ person URL");
        return personService.getAllPeople();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        log.info("POST request received to add person: {} {}", person.getFirstName(), person.getLastName());
        return personService.addPerson(person);
    }

    @PutMapping("/{firstName}/{lastName}")
    public boolean updatePerson(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody Person updatedPerson) {
        log.info("PUT request received to update person: {} {}", firstName, lastName);
        return personService.updatePerson(firstName, lastName, updatedPerson);
    }

    @DeleteMapping("/{firstName}/{lastName}")
    public String deletePerson(
            @PathVariable String firstName,
            @PathVariable String lastName
    ) {
        log.info("DELETE request received for person: {} {}", firstName, lastName);
        boolean deleted = personService.deletePerson(firstName, lastName);
        if (deleted) {
            log.info("Successfully deleted person: {} {}", firstName, lastName);
            return "Person deleted successfully.";
        } else {
            log.warn("Person not found: {} {}", firstName, lastName);
            return "Person not found!";
        }
    }
}