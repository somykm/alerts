package com.safetynet.alerts.controller;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/person")
public class PeopleController {

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        super();
        this.personService = personService;
    }

    //1. Fetch all people data (GET)
    @GetMapping("/all")
    public List<Person> getPersonList() {
        return personService.getAllPeople();
    }

    //2. add a new person (POST)
    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    //3.Update an existing person(PUT)
    @PutMapping("/{firstName}/{lastName}")
    public boolean updatePerson(
            @PathVariable String firstName,
            @PathVariable String lastName,
            @RequestBody Person updatedPerson) {

        return personService.updatePerson(firstName, lastName, updatedPerson);
    }

    //4. Delete a person (DELETE)
    @DeleteMapping("/{firstName}/{lastName}")
    public String deletePerson(
            @PathVariable String firstName,
            @PathVariable String lastName
    ) {
        boolean deleted = personService.deletePerson(firstName, lastName);
        if (deleted) {
            return "Person deleted successfully.";
        } else {
            return "Person not found!";
        }
    }
}
