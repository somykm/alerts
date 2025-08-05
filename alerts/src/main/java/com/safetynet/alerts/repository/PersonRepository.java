package com.safetynet.alerts.repository;

import com.safetynet.alerts.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private final JsonParser jsonParser;

    @Autowired
    public PersonRepository(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public List<Person> findAll() {
        return jsonParser.getAllPeople();
    }

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
        for (Person person : jsonParser.getAllPeople()) {
            if (person.getFirstName().equalsIgnoreCase(firstName) &&
                    person.getLastName().equalsIgnoreCase(lastName)) {
                return person;
            }
        }
        return null;
    }

    public void save(Person person) {
        jsonParser.getAllPeople().add(person);
    }

    public void delete(Person person) {
        List<Person> people = jsonParser.getAllPeople();
        people.remove(person);
    }

    public List<Person> findByAddressIn(List<String> addresses) {
        return jsonParser.getAllPeople().stream()
                .filter(person -> addresses.contains(person.getAddress()))
                .toList();
    }

    public List<String> getEmailsByCity(String city) {
        return jsonParser.getAllPeople().stream()
                .filter(person -> person.getCity().equalsIgnoreCase(city))
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }
}