package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {
    ObjectMapper objectMapper = new ObjectMapper();
    URL resourceUrl = getClass().getClassLoader().getResource("data.json");
    File jsonFile = new File(resourceUrl.getFile());
    List<Person> persons = new ArrayList<>();
    List<Firestation> firestations = new ArrayList<>();
    List<MedicalRecord> medicalRecords = new ArrayList<>();

    public JsonParser() {
        loadData();
        loadDataForFirestation();
        loadDataForMedicalRecord();
    }

    void loadData() {
        try {
            Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);

            if (wrapper != null && wrapper.getPersons() != null) {
                persons = wrapper.getPersons();
            } else {
                persons = new ArrayList<>();
                System.err.println("Warning: No persons found in the JSON file.");
            }
        } catch (IOException e) {
            System.err.println("Error loading data from JSON file: " + e.getMessage());
            persons = new ArrayList<>();
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

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void deletePerson(String firstName, String lastName) {
        persons.removeIf(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName));
    }

    /************************Firestation*************************************************/
    public void loadDataForFirestation() {
        try {
            Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);

            if (wrapper != null && wrapper.getFirestations() != null) {
                firestations = wrapper.getFirestations();
            } else {
                firestations = new ArrayList<>();
                System.err.println("Warning: No firestation found in the JSON file.");
            }
        } catch (IOException e) {
            System.err.println("Error loading data from JSON file: " + e.getMessage());
            firestations = new ArrayList<>();
        }
    }

    public Firestation findByAddress(String address) {
        for (Firestation firestation : getAllFirestation()) {
            if (firestation.getAddress().equals(address)) {
                return firestation;
            }
        }
        return null;
    }

    public List<Firestation> getAllFirestation() {
        return firestations;
    }

    public void addFirestation(Firestation firestation) {
        firestations.add(firestation);
    }

    void deleteFirestation(String address) {
        firestations.removeIf(p -> p.getAddress().equalsIgnoreCase(address));
    }

    /**********************************MedicalRecords***********************************************/
    void loadDataForMedicalRecord() {
        try {
            Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);

            if (wrapper != null && wrapper.getMedicalRecords() != null) {
                medicalRecords = wrapper.getMedicalRecords();
            } else {
                medicalRecords = new ArrayList<>(); // Ensure we always have a valid list
                System.err.println("Warning: No record found in the JSON file based on your search.");
            }
        } catch (IOException e) {
            System.err.println("Error loading data from JSON file: " + e.getMessage());
            medicalRecords = new ArrayList<>();
        }
    }

    public MedicalRecord findByFirstNameAndLastNameForMedicalRecord(String firstName, String lastName) {
        for (MedicalRecord medicalRecord : getAllMedicalRecords()) {
            if (medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)) {
                return medicalRecord;
            }
        }
        return null;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    public Person getPersonsByLastName(String lastName) {
        return null;
    }
}
