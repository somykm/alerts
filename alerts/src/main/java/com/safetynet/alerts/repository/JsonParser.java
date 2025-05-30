package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//repository allows you to retrieve and delete a person using as identifiers.
@Component
public class JsonParser {
    public List<Firestation> getAllFirestation;
    ObjectMapper objectMapper = new ObjectMapper();
    URL resourceUrl = getClass().getClassLoader().getResource("data-test.json");
    File jsonFile = new File(resourceUrl.getFile());
    List<MedicalRecord> medicalRecord = new ArrayList<>();
    List<Firestation> firestations = new ArrayList<>();
    List<Person> persons = new ArrayList<>();

    public JsonParser() {
        loadData();
    }

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

    public Person findByFirstNameAndLastName(String firstName, String lastName) {
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

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecord;
    }

    public List<Firestation> getAllFirestation() {
        return firestations;
    }

}
