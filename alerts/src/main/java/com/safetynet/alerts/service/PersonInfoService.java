package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.domain.PersonInfolastName;
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
public class PersonInfoService {

    private final PersonService personService;
    private final MedicalRecordService medicalRecordService;

    @Autowired
    public PersonInfoService(PersonService personService, MedicalRecordService medicalRecordService) {
        super();
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    public List<PersonInfolastName> getPersonInfoByLastName(String lastName) {
        log.info("Fetching person info for last name: {}", lastName);
        List<Person> persons = personService.getAllPeople();
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();

        List<PersonInfolastName> result = new ArrayList<>();

        for (Person person : persons) {
            if (person.getLastName().equalsIgnoreCase(lastName)) {
                PersonInfolastName info = new PersonInfolastName();
                info.setFirstName(person.getFirstName());
                info.setLastName(person.getLastName());
                info.setAddress(person.getAddress());
                info.setEmail(person.getEmail());

                for (MedicalRecord medical : medicalRecords) {
                    if (medical.getLastName().equalsIgnoreCase(lastName)) {
                        int age = calculateAge(medical.getBirthdate());
                        info.setAge(age);
                        info.setMedications(medical.getMedications());
                        info.setAllergies(medical.getAllergies());
                        log.debug("Matched medical record for: {} {}", medical.getFirstName(), medical.getLastName());
                        break;
                    }
                }
                result.add(info);
                log.info("Added person info for: {} {}", person.getFirstName(), person.getLastName());
            }
        }
        return result;
    }

    private int calculateAge(String birthdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
