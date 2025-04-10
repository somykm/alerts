package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("person")
@RestController
public class PeopleController {
    private final PersonService personService;
    public PeopleController(PersonService personService){
        this.personService = personService;
    }
    @GetMapping("/person")
    public List<Person> fetchPeople() {
        return personService.getAllPeople();
    }
    //public ModelAndView getPeople(){

   // }
    @PostMapping
    public Person addPerson(Person person){
        return personService.addPerson(person);
    }

    @PutMapping("/{firstName}/{lastName}")
    public Optional<Person> updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person updatedPerson) {
        return personService.updatePerson(firstName, lastName, updatedPerson);
    }

    @DeleteMapping("/{firstName}/{lastName")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName){
        personService.deletePerson(firstName, lastName);
    }
}
