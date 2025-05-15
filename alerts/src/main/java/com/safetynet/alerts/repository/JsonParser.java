package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Person;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//repository allows you to retrieve and delete a person using as identifiers.
@Component
public class JsonParser {
    ObjectMapper objectMapper = new ObjectMapper();
    URL resourceUrl = getClass().getClassLoader().getResource("data-test.json");
    File jsonFile = new File(resourceUrl.getFile());
   //File jsonFile = new File("alerts/src/main/resources/data-test.json");
    //    File jsonFile = new File("alerts/src/main/resources/data.json");
    List<Person> persons = new ArrayList<>();

    void loadData() {
        try {
            Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);

            if (wrapper != null && wrapper.getPersons() != null) {
                persons = wrapper.getPersons();
            } else {
                persons = new ArrayList<>(); // Ensure we always have a valid list
                System.err.println("Warning: No persons found in the JSON file.");
            }
        } catch (IOException e) {
            System.err.println("Error loading data from JSON file: " + e.getMessage());
            persons = new ArrayList<>(); // Avoid breaking the program due to JSON issues
        }
    }

//    void loadData() throws IOException {
//        Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);
//        persons = wrapper.getPersons();
//        for (Person person : wrapper.getPersons()) {
//            System.out.println("Name: " + person.getFirstName() + " " + person.getLastName());
//            System.out.println("Address: " + person.getAddress() + ", " + person.getCity() + " " + person.getZip());
//            System.out.println("Phone: " + person.getPhone());
//            System.out.println("Email: " + person.getEmail());
//        }
//
//    }

    public Person findByFirstNameAndLastName(String firstName, String lastName, List<Person> users) {
        for (Person person : getAllPeople()) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getAllPeople() {
        return persons;
    }

    void addPerson(Person person) {
        persons.add(person);
    }

    void deletePerson(String firstName, String lastName) {
        persons.removeIf(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName));
    }
}
