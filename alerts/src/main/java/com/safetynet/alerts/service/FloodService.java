package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Flood;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FloodService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PersonRepository personRepository;
    private final JsonParser jsonParser;

    @Autowired
    public FloodService(MedicalRecordRepository medicalRecordRepository, PersonRepository personRepository, JsonParser jsonParser) {
        super();
        this.medicalRecordRepository = medicalRecordRepository;
        this.personRepository = personRepository;
        this.jsonParser = jsonParser;
    }

    public Map<String, List<Flood>> getAllHouseholdByFirestation(List<String> firestationNumber) {
        Map<String, List<Flood>> groupedHouseholds = new HashMap<>();

        List<Firestation> findRelatedStations = jsonParser.getAllFirestation().stream()
                .filter(fs -> firestationNumber.contains(fs.getStation()))
                .collect(Collectors.toList());

        for (Firestation station : findRelatedStations) {
            String address = station.getAddress();
            List<Person> persons = personRepository.findByAddressIn(List.of(address));
            List<Flood> householdInFlooding = new ArrayList<>();

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
                    flood.setHouseHoldServedByStation(List.of(station.getStation()));
                    flood.setMedications(record.getMedications());
                    flood.setAllergies(record.getAllergies());

                    householdInFlooding.add(flood);
                }
            }
            groupedHouseholds.put(address, householdInFlooding);
        }
        return groupedHouseholds;
    }

    private int calculateAgeFromBirthdate(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
