package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonService {
    private final PersonRepository personRepository;//dependency

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPeople() {
        log.info("Fetching all people from repository.");
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        log.info("Adding new person: {} {}", person.getFirstName(), person.getLastName());
        personRepository.save(person);
        log.debug("Person saved: {}", person);
        return person;
    }

    public boolean updatePerson(String firstName, String lastName, Person updatedPerson) {
        log.info("Updating person: {} {}", firstName, lastName);
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (person != null) {
            person.setAddress(updatedPerson.getAddress());
            person.setCity(updatedPerson.getCity());
            person.setZip(updatedPerson.getZip());
            person.setPhone(updatedPerson.getPhone());
            person.setEmail(updatedPerson.getEmail());
            log.debug("Person updated: {}", person);
            return true;
        }
        log.warn("Person not found for update: {} {}", firstName, lastName);
        return false;
    }

    public boolean deletePerson(String firstName, String lastName) {
        log.info("Deleting person: {} {}", firstName, lastName);
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);

        if (person != null) {
            personRepository.delete(person);
            log.debug("Person deleted: {}", person);
            return true;
        }
        log.warn("Person not found for deletion: {} {}", firstName, lastName);
        return false;
    }

    public List<String> getEmailsByCity(String city) {
        log.info("Fetching emails for city: {}", city);
        return personRepository.getEmailsByCity(city);
    }

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        log.info("Searching for person: {} {}", firstName, lastName);
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        if (person != null) {
            log.debug("Person found: {}", person);
        } else {
            log.warn("Person not found: {} {}", firstName, lastName);
        }
        return person;
    }
}