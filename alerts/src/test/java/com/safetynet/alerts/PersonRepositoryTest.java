//package com.safetynet.alerts;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.List;
//import java.util.ArrayList;
//
//import com.safetynet.alerts.domain.Person;
//import com.safetynet.alerts.repository.JsonParser;
//import com.safetynet.alerts.repository.PersonRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//public class PersonRepositoryTest {
//
//    @Mock
//    private JsonParser jsonParser;
//
//    private PersonRepository personRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        personRepository = new PersonRepository(jsonParser);
//    }
//
//    @Test
//    void testFindAll() {
//        List<Person> mockPeople = List.of(new Person("John", "Doe"));
//        when(jsonParser.getAllPeople()).thenReturn(mockPeople);
//
//        List<Person> result = personRepository.findAll();
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(jsonParser, times(1)).getAllPeople();
//    }
//
//    @Test
//    void testFindByFirstNameAndLastName() {
//        Person mockPerson = new Person("John", "Doe");
//        when(jsonParser.getAllPeople()).thenReturn(List.of(mockPerson));
//
//        Person result = personRepository.findByFirstNameAndLastName("John", "Doe");
//
//        assertNotNull(result);
//        assertEquals("John", result.getFirstName());
//    }
//
//    @Test
//    void testSave() {
//        Person newPerson = new Person("Sarah", "Smith");
//        List<Person> mockPeople = new ArrayList<>();
//        when(jsonParser.getAllPeople()).thenReturn(mockPeople);
//
//        personRepository.save(newPerson);
//
//        assertEquals(1, mockPeople.size());
//    }
//
//    @Test
//    void testDelete() {
//        Person existingPerson = new Person("John", "Doe");
//        List<Person> mockPeople = new ArrayList<>(List.of(existingPerson));
//        when(jsonParser.getAllPeople()).thenReturn(mockPeople);
//
//        personRepository.delete(existingPerson);
//
//        assertEquals(0, mockPeople.size());
//    }
//}
//
