package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Flood;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FloodService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonRepository personRepository;

    @Autowired
    public FloodService(MedicalRecordRepository medicalRecordRepository, PersonRepository personRepository) {
        super();
        this.medicalRecordRepository = medicalRecordRepository;
        this.personRepository = personRepository;
    }

    public Map<String, List<Flood>> getAllHouseholdByFirestation(Firestation firestation) {
        Map<String, List<Flood>> groupedHouseholds = new HashMap<>();
        String address = firestation.getAddress();
        List<Person> persons = personRepository.findByAddressIn(List.of(firestation.getAddress()));
        List<Flood> householdInFlooding = groupedHouseholds.getOrDefault(address, new ArrayList<>());

        for (Person person : persons) {
            MedicalRecord record = medicalRecordRepository.findByFirstNameAndLastName(
                    person.getFirstName(), person.getLastName()
            );
            if (record != null) {
                int age = calculateAgeFromBirthdate(record.getBirthdate());
                Flood flood = new Flood();
                flood.setFirstName(person.getFirstName());
                flood.setLastName(person.getLastName());
                flood.setPhone(person.getPhone());
                flood.setAge(age);
                flood.setAddress(person.getAddress());
                flood.setHouseHoldServedByStation(List.of(address));
                flood.setMedications(record.getMedications());
                flood.setAllergies(record.getAllergies());

                householdInFlooding.add(flood);
            }
        }
        groupedHouseholds.put(address, householdInFlooding);
        return groupedHouseholds;
    }

    private int calculateAgeFromBirthdate(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
