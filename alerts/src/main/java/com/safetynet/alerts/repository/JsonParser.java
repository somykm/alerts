package com.safetynet.alerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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
                log.info("Loaded {} persons from JSON.", persons.size());

            } else {
                persons = new ArrayList<>();
                log.warn("No persons found in the JSON file.");
            }
        } catch (IOException e) {
            log.error("Error loading persons from JSON file: {}", e.getMessage());
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
        log.info("Person added: {} {}", person.getFirstName(), person.getLastName());
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
                log.info("Loaded {} firestations from JSON.", firestations.size());

            } else {
                firestations = new ArrayList<>();
                log.warn("No firestations found in the JSON file.");
            }
        } catch (IOException e) {
            log.error("Error loading firestations from JSON file: {}", e.getMessage());
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
        log.info("Added firestation at address: {}", firestation.getAddress());
    }

    void deleteFirestation(String address) {
        firestations.removeIf(
                p -> p.getAddress().equalsIgnoreCase(address));
        log.info("Deleted firestation at address: {}", address);
    }

    /**********************************MedicalRecords***********************************************/
    void loadDataForMedicalRecord() {
        try {
            Wrapper wrapper = objectMapper.readValue(jsonFile, Wrapper.class);

            if (wrapper != null && wrapper.getMedicalRecords() != null) {
                medicalRecords = wrapper.getMedicalRecords();
                log.info("Loaded {} medical records from JSON.", medicalRecords.size());
            } else {
                medicalRecords = new ArrayList<>(); // Ensure we always have a valid list
                log.warn("No medical records found in the JSON file.");
            }
        } catch (IOException e) {
            log.error("Error loading medical records from JSON file: {}", e.getMessage());
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
        log.info("Added medical record for person: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
    }

    public Person getPersonsByLastName(String lastName) {
        return null;
    }
}
