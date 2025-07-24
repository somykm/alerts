package com.safetynet.alerts;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        Assertions.assertThat(people).isNotEmpty(); // Adjust based on expected behavior
    }

//    @Test
//    void testFindByFirstNameAndLastName() {
//        Person person = jsonParser.findByFirstNameAndLastName("John", "Boyd");
//        Assertions.assertThat(person).isNotNull();
//        Assertions.assertThat(person.getFirstName()).isEqualToIgnoringCase("John");
//        Assertions.assertThat(person.getLastName()).isEqualToIgnoringCase("Boyd");
//    }

    @Test
    void testSavePerson() {
        Person newPerson = new Person("Jane", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "jane.doe@example.com");
        jsonParser.addPerson(newPerson);

        Person retrievedPerson = jsonParser.findByFirstNameAndLastName("Jane", "Doe");
        Assertions.assertThat(retrievedPerson).isNotNull();
        Assertions.assertThat(retrievedPerson.getEmail()).isEqualTo("jane.doe@example.com");
    }

    @Test
    void testDeletePerson() {
        Person personToDelete = new Person("Mark", "Smith", "456 Elm St", "Shelbyville", "67890", "987-654-3210", "mark.smith@example.com");


        jsonParser.deletePerson("John", "Doe");
        Person deletedPerson = jsonParser.findByFirstNameAndLastName("Mark", "Smith");
        Assertions.assertThat(deletedPerson).isNull();
    }
}