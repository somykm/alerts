package com.safetynet.alerts.controller;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/person")
public class PeopleController {
    //for modeAndView
    //private List<Person> personInfo = new ArrayList<Person>();// Store the people dynamically

    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping
//    public List<Person> fetchPeople() {
//        return personService.getAllPeople();
//    }
//
//    @PostMapping("/person")
//    public Person addPerson(@RequestBody Person person) {
//        return personService.addPerson(person);
//    }


//    @PutMapping("/{firstName}/{lastName}")
//    public Optional<Person> updatePerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person updatedPerson) {
//        //return personService.updatePerson(firstName, lastName, updatedPerson);
//
//        PersonRepository personRepository = null;
//        return personRepository.findByFirstNameAndLastName(firstName, lastName)
//                .get(person -> {
//                    person.setAddress(updatedPerson.getAddress());
//                    person.setCity(updatedPerson.getCity());
//                    person.setZip(updatedPerson.getZip());
//                    person.setEmail(updatedPerson.getEmail());
//                    person.setPhone(updatedPerson.getPhone());
//                    return personRepository.save(person);
//                });
//    }

    @DeleteMapping("/delete")
    public String deletePerson(@RequestParam String firstName, @RequestParam String lastName) {
        try {
            personService.deletePerson(firstName, lastName);
            return "Person deleted successfully.";
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }


    @GetMapping("/all")
    public List<Person> getPersonInfo() {
        return personService.getAllPeople();
    }

//    public void setPersonInfo(List<Person> personInfo) {
//        this.personInfo = personInfo;
//    }
}
