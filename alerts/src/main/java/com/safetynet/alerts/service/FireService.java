package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.Fire;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Fire> getResidentAndStationByAddress(List<String> addresses) {
        List<Fire> result = new ArrayList<>();

        for (String address : addresses) {
            List<Person> persons = personRepository.findByAddressIn(Arrays.asList(address));
            Firestation firestation = firestationService.getFirestationByAddress(address);

            for (Person person : persons) {
                MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(
                        person.getFirstName(), person.getLastName()
                );
                int age = calculateAgeFromBirthdate(medicalRecord.getBirthdate());

                Fire fire = new Fire();
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
//    public List<Fire> getResidentAndStationByAddress(List<String> address) {
//        List<Person> persons = personService.findByAddress(address);
//        Firestation firestation = firestationService.getFirestationByAddress(address);
//
//
//        List<Fire> result = new ArrayList<>();
//
//        for (Person person : persons) {
//            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(
//                    person.getFirstName(),
//                    person.getLastName()
//            );
//            int age = calculateAgeFromBirthdate(medicalRecord.getBirthdate());
//            Fire fire = new Fire();
//            fire.setStation(firestation.getStation());
//            fire.setAddress(person.getAddress());
//            fire.setFirstName(person.getFirstName());
//            fire.setLastName(person.getLastName());
//            fire.setPhone(person.getPhone());
//            fire.setAge(age);
//
//            fire.setMedications(medicalRecord.getMedications());
//            fire.setAllergies(medicalRecord.getAllergies());
//
//            result.add(fire);
//        }
//        return result;
//    }

    private int calculateAgeFromBirthdate(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}

