//package com.safetynet.alerts;
//
//import com.safetynet.alerts.domain.Person;
//import com.safetynet.alerts.repository.PersonRepository;
//import com.safetynet.alerts.service.PersonService;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@WebMvcTest
//@ExtendWith(MockitoExtension.class)
//public class PersonServiceTest {
//    @Mock
//    private PersonRepository personRepositoryMock;
//
//    @InjectMocks
//    private PersonService personService;// Inject mocks
//
//    @Test
//    public void testGetAllPeopleReturnsAllPeopleFromRepository() {
//        //arrange (prepare test data)
//        Person person1 = new Person("Sara", "Smith", "1235 Ronake rd",
//                "Silver Spring", "20902", "240-777-5152", "sara@gmail.com");
//        Person person2 = new Person("Ali", "Johnson", "3450 Ross Street",
//                "Silver Spring", "20912", "202-527-5050", "ali_J@gmail.com");
//        List<Person> mockPerson = Arrays.asList(person1, person2);
//
//        when(personRepositoryMock.findAll()).thenReturn(mockPerson);
//        //act
//        List<Person> result = personService.getAllPeople();
//        //assert
//        assertEquals(2, result.size());
//        assertEquals("Sara", result.get(0).getFirstName());
//        assertEquals("Ali", result.get(1).getFirstName());
//    }
//
//    @Test
//    public void testAddPersonReturnsNewPersonToRepository() {
//        //arrange
//        Person newPerson = new Person("Jack", "Jackson", "4500 White oak rd",
//                "Silver Spring", "20901", "202-555-7777", "Jacky@gmail.com");
//
//        Person result = personService.addPerson(newPerson);
//
//        verify(personRepositoryMock).save(newPerson);
//        assertEquals(newPerson, result);
//    }
//
//    @Test
//    public void testDeletePersonIfExistFromRepositoryAndReturnTrue() {
//        //Arrange
//        String firstName = "Sara";
//        String lastName = "Smith";
//        Person existPerson = new Person(firstName, lastName, "123 Main St",
//                "Bethesda", "20814", "301-555-1234", "emily@example.com");
//        when(personRepositoryMock.findByFirstNameAndLastName(firstName, lastName)).thenReturn(existPerson);
//        //act
//        boolean result = personService.deletePerson(firstName, lastName);
//
//        //assert
//        verify(personRepositoryMock).delete(existPerson);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testUpdatePersonIfExistsReturnsTrueAndUpdatesValues() {
//        String firstName = "Sara";
//        String lastName = "Smith";
//        Person existing = new Person(firstName, lastName, "Old Addr", "Old City", "00000", "000-000-0000", "old@example.com");
//        Person updated = new Person(firstName, lastName, "New Addr", "New City", "12345", "111-111-1111", "new@example.com");
//
//        when(personRepositoryMock.findByFirstNameAndLastName(firstName, lastName)).thenReturn(existing);
//
//        boolean result = personService.updatePerson(firstName, lastName, updated);
//
//        assertTrue(result);
//        assertEquals("New Addr", existing.getAddress());
//        assertEquals("New City", existing.getCity());
//        assertEquals("12345", existing.getZip());
//        assertEquals("111-111-1111", existing.getPhone());
//        assertEquals("new@example.com", existing.getEmail());
//
//    }
//
//    @Test
//    public void testGetEmailsByCityReturnsAllEmailsInThatCityFromRepository() {
//        //arrange
//        String city = "Culver";
//        List<String> mockEmails = Arrays.asList("sara@gmail.com", "jackyJ@gmail.com");
//        Mockito.when(personRepositoryMock.getEmailsByCity(city)).thenReturn(mockEmails);
//
//        //act
//        List<String> result = personService.getEmailsByCity(city);
//        //assert
//        verify(personRepositoryMock).getEmailsByCity(city);
//        assertEquals(mockEmails, result);
//    }
//
//    @Test
//    public void testUpdatePersonIfNotFoundReturnsFalse() {
//        when(personRepositoryMock.findByFirstNameAndLastName("Noah", "Smith")).thenReturn(null);
//
//        boolean result = personService.updatePerson("Noah", "Smith", new Person());
//
//        assertFalse(result);
//    }
//
//    @Test
//    public void testFindByFirstNameAndLastNameReturnsPerson() {
//        String firstName = "Ali";
//        String lastName = "Johnson";
//        Person expected = new Person(firstName, lastName, "Addr", "City", "Zip", "Phone", "Email");
//
//        when(personRepositoryMock.findByFirstNameAndLastName(firstName, lastName)).thenReturn(expected);
//
//        Person result = personService.findByFirstNameAndLastName(firstName, lastName);
//
//        assertEquals(expected, result);
//    }
//
//}
