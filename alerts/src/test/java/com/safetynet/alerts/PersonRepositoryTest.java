package com.safetynet.alerts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

    @Mock
    private JsonParser jsonParserMock;
    @InjectMocks
    private PersonRepository personRepository;

    @Test
    public void testFindAllReturnsAllPeopleFromJsonParser() {
        Person personA = new Person("John", "Doe", "4700 White oak",
                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");

        List<Person> mockList = Collections.singletonList(personA);
        when(jsonParserMock.getAllPeople()).thenReturn(mockList);
        //act
        List<Person> result = personRepository.findAll();
        //assert
        assertNotNull(result);
        Assert.assertTrue(result.size() == 1);
        Assert.assertTrue(result.get(0).getFirstName().equals("John"));
        verify(jsonParserMock, times(1)).getAllPeople();

    }

    @Test
    public void testFindByFirstNameAndLastName() {
        Person mockPerson = new Person("John", "Doe", "4700 White oak apt 2",
                "Silver Spring", "20815", "202-123-5555", "Johnny@gmail.com");
        when(jsonParserMock.getAllPeople()).thenReturn(Collections.singletonList(mockPerson));

        Person result = personRepository.findByFirstNameAndLastName("John", "Doe");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    public void testSaveToAddPerson() {
        // Arrange
        Person newPerson = new Person("Sarah", "Clark", "4700 bradley apt 7",
                "Chevy Chase", "20812", "202-333-8190", "sarah@yahoo.com");
        List<Person> people = new ArrayList<>();
        when(jsonParserMock.getAllPeople()).thenReturn(people);

        // Act
        personRepository.save(newPerson);

        // Assert
        assertTrue(people.contains(newPerson));
    }
    @Test
    public void testDeleteToRemovePerson() {
        // Arrange
        Person existingPerson = new Person("John", "Doe", "4700 White oak",
                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");
        List<Person> people = new ArrayList<>();
        people.add(existingPerson);
        when(jsonParserMock.getAllPeople()).thenReturn(people);

        // Act
        personRepository.delete(existingPerson);

        // Assert
        assertFalse(people.contains(existingPerson));
    }

}

