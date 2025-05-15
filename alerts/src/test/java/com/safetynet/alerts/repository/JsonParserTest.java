//package com.safetynet.alerts.repository;
//
//import com.safetynet.alerts.domain.Person;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringJUnitConfig(JsonParser.class)
//@SpringBootTest
//class JsonParserTest {
//    @Autowired
//    private JsonParser jsonParser;
//
//    @Test
//    void testConstructor() {
//        assertThat(jsonParser).isNotNull();
//    }
//
//    @Test
//    void testGetAllPeople() {
//        List<Person> people = jsonParser.getAllPeople();
//        assertThat(people).isNotNull();
//        assertThat(people).isNotEmpty(); // Adjust based on expected behavior
//    }
//
//    @Test
//    void testFindByFirstNameAndLastName() {
//        Person person = jsonParser.findByFirstNameAndLastName("John", "Doe");
//        assertThat(person).isNotNull();
//        assertThat(person.getFirstName()).isEqualToIgnoringCase("John");
//        assertThat(person.getLastName()).isEqualToIgnoringCase("Doe");
//    }
//
//    @Test
//    void testSavePerson() {
//        Person newPerson = new Person("Jane", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "jane.doe@example.com");
//
//
//        Person retrievedPerson = jsonParser.findByFirstNameAndLastName("Jane", "Doe");
//        assertThat(retrievedPerson).isNotNull();
//        assertThat(retrievedPerson.getEmail()).isEqualTo("jane.doe@example.com");
//    }
//
//    @Test
//    void testDeletePerson() {
//        Person personToDelete = new Person("Mark", "Smith", "456 Elm St", "Shelbyville", "67890", "987-654-3210", "mark.smith@example.com");
//
//
//        jsonParser.deletePerson(personToDelete);
//        Person deletedPerson = jsonParser.findByFirstNameAndLastName("Mark", "Smith");
//        assertThat(deletedPerson).isNull();
//    }
//}