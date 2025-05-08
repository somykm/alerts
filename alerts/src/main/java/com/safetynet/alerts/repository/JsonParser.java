package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//repository allows you to retrieve and delete a person using as identifiers.
@Component
@Repository
public class JsonParser {
    ObjectMapper objectMapper = new ObjectMapper();
    //File jsonFile = new File("alerts/src/main/resources/data-test.json");
    File jsonFile = new File("alerts/src/main/resources/data.json");
    List<Person> persons = new ArrayList<>();

    public JsonParser() throws IOException {
        loadData();
    }

    void loadData() throws IOException {
        Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);
        persons = wrapper.getPersons();
        for (Person person : wrapper.getPersons()) {
            System.out.println("Name: " + person.getFirstName() + " " + person.getLastName());
            System.out.println("Address: " + person.getAddress() + ", " + person.getCity() + " " + person.getZip());
            System.out.println("Phone: " + person.getPhone());
            System.out.println("Email: " + person.getEmail());
        }

    }

    public List<Person> getAllPeople() {
        return persons;
    }

    void addPerson(Person person) {
        persons.add(person);
        saveData();
    }

    void deletePerson(String firstName, String lastName) {
        persons.removeIf(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName));
        saveData();
    }

    void saveData() {
        try {
            objectMapper.writeValue(jsonFile, persons); // Use wrapper to maintain structure
            System.out.println("Data successfully saved to data.json");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
