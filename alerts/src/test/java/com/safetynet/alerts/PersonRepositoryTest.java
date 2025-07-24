package com.safetynet.alerts;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PersonRepositoryTest {

    @Mock
    private JsonParser jsonParserMock;

    @InjectMocks
    private PersonRepository personRepository;

    private Person personA, personB;

    @BeforeEach
    void setUp() {
        personA = new Person("John", "Doe", "4700 White oak",
                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");
        personB = new Person("Sara", "Smith", "1235 White oak apt 12",
                "Silver Spring", "20812", "202-555-5757", "saraSmith@gmail.com");
    }

    @Test
    void testFindByFirstNameAndLastNameReturnsCorrectPerson() {
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(List.of(personA));

        Person result = personRepository.findByFirstNameAndLastName("John", "Doe");

        assertNotNull(result);
        assertEquals("Johnny@gmail.com", result.getEmail());
    }

    @Test
    void testFindByFirstNameAndLastNameReturnsNullIfNotFound() {
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(List.of(personA));

        Person result = personRepository.findByFirstNameAndLastName("Jane", "Doe");

        assertNull(result);
    }

    @Test
    void testSaveAddsPersonToList() {
        List<Person> people = new ArrayList<>();
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(people);

        personRepository.save(personB);

        assertTrue(people.contains(personB));
    }

    @Test
    void testDeleteRemovesPersonFromList() {
        List<Person> people = new ArrayList<>(List.of(personA));
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(people);

        personRepository.delete(personA);

        assertFalse(people.contains(personA));
    }

    @Test
    void testGetEmailsByCityFiltersCorrectly() {
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(List.of(personA, personB));

        List<String> result = personRepository.getEmailsByCity("Silver Spring");

        assertEquals(2, result.size());
        assertTrue(result.contains("Johnny@gmail.com"));
        assertTrue(result.contains("saraSmith@gmail.com"));
    }

    @Test
    void testFindByAddressInFiltersMatchingAddresses() {
        List<String> targetAddresses = List.of("4700 White oak", "Unknown address");
        Mockito.when(jsonParserMock.getAllPeople()).thenReturn(List.of(personA, personB));

        List<Person> result = personRepository.findByAddressIn(targetAddresses);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }
}