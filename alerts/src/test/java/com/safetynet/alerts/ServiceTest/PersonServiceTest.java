package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test
    void testGetAllPeopleReturnsAllPeopleFromRepository() {
        Person person1 = new Person("Sara", "Smith", "1235 Ronake rd",
                "Silver Spring", "20902", "240-777-5152", "sara@gmail.com");
        Person person2 = new Person("Ali", "Johnson", "3450 Ross Street",
                "Silver Spring", "20912", "202-527-5050", "ali_J@gmail.com");

        when(personRepository.findAll()).thenReturn(Arrays.asList(person1, person2));

        List<Person> result = personService.getAllPeople();

        assertEquals(2, result.size());
        assertEquals("Sara", result.get(0).getFirstName());
        assertEquals("Ali", result.get(1).getFirstName());
    }

    @Test
    void testAddPersonReturnsNewPersonToRepository() {
        Person newPerson = new Person("Jack", "Jackson", "4500 White oak rd",
                "Silver Spring", "20901", "202-555-7777", "Jacky@gmail.com");

        Person result = personService.addPerson(newPerson);

        verify(personRepository).save(newPerson);
        assertEquals(newPerson, result);
    }

    @Test
    void testDeletePersonIfExistFromRepositoryAndReturnTrue() {
        String firstName = "Sara";
        String lastName = "Smith";
        Person existPerson = new Person(firstName, lastName, "123 Main St",
                "Bethesda", "20814", "301-555-1234", "emily@example.com");

        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(existPerson);

        boolean result = personService.deletePerson(firstName, lastName);

        verify(personRepository).delete(existPerson);
        assertTrue(result);
    }

    @Test
    void testUpdatePersonIfExistsReturnsTrueAndUpdatesValues() {
        String firstName = "Sara";
        String lastName = "Smith";
        Person existing = new Person(firstName, lastName, "Old Addr", "Old City", "00000", "000-000-0000", "old@example.com");
        Person updated = new Person(firstName, lastName, "New Addr", "New City", "12345", "111-111-1111", "new@example.com");

        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(existing);

        boolean result = personService.updatePerson(firstName, lastName, updated);

        assertTrue(result);
        assertEquals("New Addr", existing.getAddress());
        assertEquals("New City", existing.getCity());
        assertEquals("12345", existing.getZip());
        assertEquals("111-111-1111", existing.getPhone());
        assertEquals("new@example.com", existing.getEmail());
    }

    @Test
    void testUpdatePersonIfNotFoundReturnsFalse() {
        when(personRepository.findByFirstNameAndLastName("Noah", "Smith")).thenReturn(null);

        boolean result = personService.updatePerson("Noah", "Smith", new Person());

        assertFalse(result);
    }

    @Test
    void testGetEmailsByCityReturnsAllEmailsInThatCityFromRepository() {
        String city = "Culver";
        List<String> mockEmails = Arrays.asList("sara@gmail.com", "jackyJ@gmail.com");

        when(personRepository.getEmailsByCity(city)).thenReturn(mockEmails);

        List<String> result = personService.getEmailsByCity(city);

        verify(personRepository).getEmailsByCity(city);
        assertEquals(mockEmails, result);
    }

    @Test
    void testFindByFirstNameAndLastNameReturnsPerson() {
        String firstName = "Ali";
        String lastName = "Johnson";
        Person expected = new Person(firstName, lastName, "Addr", "City", "Zip", "Phone", "Email");

        when(personRepository.findByFirstNameAndLastName(firstName, lastName)).thenReturn(expected);

        Person result = personService.findByFirstNameAndLastName(firstName, lastName);

        assertEquals(expected, result);
    }
}