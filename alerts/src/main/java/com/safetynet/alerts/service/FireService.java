package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.FireResidents;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FireService {
    private final PersonRepository personRepository;
    private final FirestationService firestationService;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public FireService(PersonRepository personRepository, FirestationService firestationService, MedicalRecordRepository medicalRecordRepository) {
        super();
        this.personRepository = personRepository;
        this.firestationService = firestationService;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<FireResidents> getResidentAndStationByAddress(List<String> addresses) {
        log.info("Fetching fire residents and station info for addresses: {}", addresses);
        List<FireResidents> result = new ArrayList<>();

        for (String address : addresses) {
            List<Person> persons = personRepository.findByAddressIn(Arrays.asList(address));
            log.info("Found {} residents at address {}", persons.size(), address);
            Firestation firestation = firestationService.getFirestationByAddress(address);
            if (firestation == null) {
                log.warn("No firestation found for this address: {}", address);
                continue;
            }

            for (Person person : persons) {
                MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName()
                );
                if (medicalRecord == null) {
                    log.warn("No medical record found for {} {}", person.getFirstName(), person.getLastName());
                    continue;
                }

                int age = calculateAgeFromBirthdate(medicalRecord.getBirthdate());

                FireResidents fire = new FireResidents();
                fire.setStation(firestation.getStation());
                fire.setAddress(person.getAddress());
                fire.setFirstName(person.getFirstName());
                fire.setLastName(person.getLastName());
                fire.setPhone(person.getPhone());
                fire.setAge(age);
                fire.setMedications(medicalRecord.getMedications());
                fire.setAllergies(medicalRecord.getAllergies());

                result.add(fire);
            }
        }

        return result;
    }

    private int calculateAgeFromBirthdate(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}

