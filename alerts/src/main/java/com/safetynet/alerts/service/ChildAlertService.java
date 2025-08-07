package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.ChildAlert;
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
import java.util.List;

@Slf4j
@Service
public class ChildAlertService {

    private final PersonRepository personRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public ChildAlertService(PersonRepository personRepository, MedicalRecordRepository medicalRecordRepository) {
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<ChildAlert> getChildInfoByAddress(String address) {
        log.info("Fetching child alert information for address: {}", address);
        List<Person> residents = personRepository.findByAddressIn(List.of(address));
        log.info("Found {} residents at address {}", residents.size(), address);
        List<ChildAlert> results = new ArrayList<>();

        for (Person person : residents) {
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(
                    person.getFirstName(), person.getLastName()
            );
            if (medicalRecord == null) {
                log.warn("No medical record found for {} {}", person.getFirstName(), person.getLastName());
                continue;
            }
            int age = calculateAgeFromBirthdate(medicalRecord.getBirthdate());
            log.debug("Calculated age for {} {}: {}", person.getFirstName(), person.getLastName(), age);
            if (age <= 18) {
                log.info("Child found: {} {}, age {}", person.getFirstName(), person.getLastName(), age);
                List<String> otherHouseHoldMember = new ArrayList<>();
                for (Person otherMember : residents) {
                    if (!otherMember.getFirstName().equalsIgnoreCase(person.getFirstName()) ||
                            !otherMember.getLastName().equalsIgnoreCase(person.getLastName())) {
                        otherHouseHoldMember.add(otherMember.getFirstName() + " " + otherMember.getLastName());
                    }
                }
                ChildAlert childAlert = new ChildAlert();
                childAlert.setFirstName(person.getFirstName());
                childAlert.setLastName(person.getLastName());
                childAlert.setAge(age);
                childAlert.setHouseholdMembers(otherHouseHoldMember);

                results.add(childAlert);
            }
        }
        log.info("Returning {} child alert(s) for address {}", results.size(), address);
        return results;
    }

    public int calculateAgeFromBirthdate(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
