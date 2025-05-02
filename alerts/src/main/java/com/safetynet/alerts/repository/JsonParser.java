package com.safetynet.alerts.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//repository allows you to retrieve and delete a person using  and  as identifiers.
@Repository
public class JsonParser {
    ObjectMapper objectMapper = new ObjectMapper();
    File jsonFile = new File("alerts/src/main/resources/data-test.json");
    //File jsonFile = new File("alerts/src/main/resources/data.json");
    List<Person> persons = new ArrayList<>();

    public JsonParser() throws IOException {
        loadData();
    }

    void loadData() throws IOException {
       //persons = objectMapper.readValue(jsonFile, new TypeReference<List<Person>>() {
      // });
        Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);
        persons = wrapper.getPersons();
        // Access and print the details
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
