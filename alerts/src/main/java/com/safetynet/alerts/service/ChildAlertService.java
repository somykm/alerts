package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.ChildAlert;
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
import java.util.List;

/*http://localhost:8080/childAlert?address=<address>
This URL must return a list of children (any individual aged 18 years or younger) living
at this address. The list must include the first name, last name of each child,
age, and a list of other household members. If no children are found, this URL may
return an empty string.
*/
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
        List<Person> residents = personRepository.findByAddressIn(List.of(address));
        List<ChildAlert> results = new ArrayList<>();

        for (Person person : residents) {
            // Get medical record to find birthdate
            MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(
                    person.getFirstName(), person.getLastName()
            );
            if (medicalRecord == null) {
                continue;
            }
            int age = calculateAgeFromBirthdate(medicalRecord.getBirthdate());
            if (age <= 18) {
                List<String> otherHouseHoldMember = new ArrayList<>();
                for (Person otherMember : residents) {
                    if (!otherMember.getFirstName().equalsIgnoreCase(person.getFirstName()) ||
                            !otherMember.getLastName().equalsIgnoreCase(person.getLastName())) {
                        // Add full name of household member
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
        return results;
    }
    public int calculateAgeFromBirthdate(String birthdate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dob = LocalDate.parse(birthdate, formatter);
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
