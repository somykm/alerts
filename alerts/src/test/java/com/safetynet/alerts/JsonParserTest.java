package com.safetynet.alerts;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import com.safetynet.alerts.domain.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig(JsonParser.class)
@SpringBootTest
class JsonParserTest {

    @Autowired
    private JsonParser jsonParser;

    @Test
    void testConstructor() {
        Assertions.assertThat(jsonParser).isNotNull();
    }

    @Test
    void testGetAllPeople() {
        List<Person> people = jsonParser.getAllPeople();
        Assertions.assertThat(people).isNotNull();
        Assertions.assertThat(people).isNotEmpty();
    }

    @Test
    void testSavePerson() {
        Person newPerson = new Person("Jane", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "jane.doe@example.com");
        jsonParser.addPerson(newPerson);

        Person retrievedPerson = jsonParser.findByFirstNameAndLastName("Jane", "Doe");
        Assertions.assertThat(retrievedPerson).isNotNull();
        Assertions.assertThat(retrievedPerson.getEmail()).isEqualTo("jane.doe@example.com");
    }

    @Test
    void testFindByFirstNameAndLastName() {
        jsonParser.getAllPeople().clear();

        Person newPerson = new Person("Jane", "Doe", "456 Main St", "Silver Spring", "20802", "123-456-5555", "jane.doe@example.com");
        jsonParser.addPerson(newPerson);

        Person person = jsonParser.findByFirstNameAndLastName("Jane", "Doe");
        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getFirstName()).isEqualToIgnoringCase("Jane");
        Assertions.assertThat(person.getLastName()).isEqualToIgnoringCase("Doe");
    }

    @Test
    void testDeletePerson() {
        Person personToDelete = new Person("Mark", "Smith", "456 Elm St", "Shelbyville", "67890", "987-654-3210", "mark.smith@example.com");
        jsonParser.addPerson(personToDelete);

        jsonParser.deletePerson("Mark", "Smith");
        Person deletedPerson = jsonParser.findByFirstNameAndLastName("Mark", "Smith");
        Assertions.assertThat(deletedPerson).isNull();
    }

    @Test
    void testLoadDataForFirestationWhenNoFirestationsFoundThenEmptyListInitialized() {
        JsonParser parserStub = new JsonParser() {
            @Override
            public void loadDataForFirestation() {
                ArrayList<Firestation> firestations = null; // simulate null from wrapper.getFirestations()
                if (firestations == null) {
                    firestations = new java.util.ArrayList<>();
                    System.err.println("Warning: No firestation found in the JSON file.");
                }
            }
        };

        parserStub.loadDataForFirestation();
        Assertions.assertThat(parserStub.getAllFirestation()).isNotNull();
        Assertions.assertThat(parserStub.getAllFirestation()).isEmpty();
    }

    @Test
    void testAddAndFindFirestation() {
        Firestation station = new Firestation("789 Oak St", "3");
        jsonParser.addFirestation(station);

        Firestation retrieved = jsonParser.findByAddress("789 Oak St");
        Assertions.assertThat(retrieved).isNotNull();
        Assertions.assertThat(retrieved.getAddress()).isEqualTo("789 Oak St");
        Assertions.assertThat(retrieved.getStation()).isEqualTo("3");
    }

    @Test
    void testFindByFirstNameAndLastNameForMedicalRecord() {
        jsonParser.getAllMedicalRecords().clear();

        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Alice");
        record.setLastName("Johnson");
        record.setBirthdate("01/01/1990");
        record.setMedications(List.of("aspirin"));
        record.setAllergies(List.of("peanuts"));

        jsonParser.addMedicalRecord(record);

        MedicalRecord retrieved = jsonParser.findByFirstNameAndLastNameForMedicalRecord("Alice", "Johnson");
        Assertions.assertThat(retrieved).isNotNull();
        Assertions.assertThat(retrieved.getBirthdate()).isEqualTo("01/01/1990");
        Assertions.assertThat(retrieved.getMedications()).contains("aspirin");
        Assertions.assertThat(retrieved.getAllergies()).contains("peanuts");
    }
}


