package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //    public boolean updatePerson(String firstName, String lastName, Person updatedPerson) {
//        return false;
//    }
    public boolean updatePerson(String firstName, String lastName, Person updatedPerson) {
        Person existingPerson = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (existingPerson != null) {
            existingPerson.setAddress(updatedPerson.getAddress());
            existingPerson.setCity(updatedPerson.getCity());
            existingPerson.setZip(updatedPerson.getZip());
            existingPerson.setPhone(updatedPerson.getPhone());
            existingPerson.setEmail(updatedPerson.getEmail());

            personRepository.save(existingPerson); // Persist the changes
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
