package com.safetynet.alerts.service;

import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final List<Person> people =new ArrayList<>();
    public Person addPerson(Person person){
        people.add(person);
        return person;
    }
    //get all people
    public List<Person> getAllPeople(){
        return people;
    }
    public Person findPersonByName(String name){
        return people.stream()
                .filter(person ->person.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }


    public Optional<Person> updatePerson(String firstName, String lastName, Person updatedPerson) {
        return people.stream()
                .filter(person -> person.getFirstName().equalsIgnoreCase(firstName)
                        && person.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .map(existingPerson -> {
                    people.remove(existingPerson);
                    people.add(updatedPerson);
                    return updatedPerson;
                });

    }

    public void deletePerson(String firstName, String lastName) {
        people.removeIf(person -> person.getFirstName().equalsIgnoreCase(firstName)
                && person.getLastName().equalsIgnoreCase(lastName));
    }
}
