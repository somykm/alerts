package com.safetynet.alerts;

import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.PersonService;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest
@RunWith(MockitoJUnitRunner.class)//to mock the dependencies of personService
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepositoryMock;


    @InjectMocks
    private PersonService personService;// Inject mocks

    @Test
    public void testGetAllPeopleReturnsAllPeopleFromRepository() {
        //arrange (prepare test data)
        Person person1 = new Person("Sara", "Smith", "1235 Ronake rd",
                "Silver Spring", "20902", "240-777-5152", "sara@gmail.com");
        Person person2 = new Person("Ali", "Johnson", "3450 Ross Street",
                "Silver Spring", "20912", "202-527-5050", "ali_J@gmail.com");
        List<Person> mockPerson = Arrays.asList(person1, person2);

        when(personRepositoryMock.findAll()).thenReturn(mockPerson);
        //act
        List<Person> result = personService.getAllPeople();
        //assert
        Assert.assertTrue(result.size() == 2);
        Assert.assertTrue(result.get(0).getFirstName().equals("Sara"));
        Assert.assertTrue(result.get(1).getFirstName().equals("Ali"));
    }

    @Test
    public void testAddPersonReturnsNewPersonToRepository() {
        //arrange
        Person newPerson = new Person("Jack", "Jackson", "4500 White oak rd",
                "Silver Spring", "20901", "202-555-7777", "Jacky@gmail.com");

        //act
        Person result = personService.addPerson(newPerson);

        //assert
        Mockito.verify(personRepositoryMock).save(newPerson);
        Assert.assertEquals(newPerson, result);
    }

    @Test
    public void testDeletePersonIfExistFromRepositoryAndReturnTrue() {
        //Arrange
        String firstName = "Sara";
        String lastName = "Smith";
        Person existPerson = new Person(firstName, lastName, "123 Main St",
                "Bethesda", "20814", "301-555-1234", "emily@example.com");
        Mockito.when(personRepositoryMock.findByFirstNameAndLastName(firstName, lastName)).thenReturn(existPerson);

        //act
        boolean result = personService.deletePerson(firstName, lastName);

        //assert
        Mockito.verify(personRepositoryMock).delete(existPerson);
        Assert.assertTrue(result);
    }

    @Test
    public void testDeletePersonIfNotExistResultFalse() {
        String firstname = "Noah";
        String lastName = "Gonzalez";

        Mockito.when(personRepositoryMock.findByFirstNameAndLastName(firstname, lastName)).thenReturn(null);

        //act
        boolean result = personService.deletePerson(firstname, lastName);

        //assert

    }

    @Test
    public void testGetEmailsByCityReturnsAllEmailsInThatCityFromRepository() {
        //arrange
        String city = "Culver";
        List<String> mockEmails = Arrays.asList("sara@gmail.com", "jackyJ@gmail.com");
        Mockito.when(personRepositoryMock.getEmailsByCity(city)).thenReturn(mockEmails);

        //act
        List<String> result = personService.getEmailsByCity(city);
        //assert
        Mockito.verify(personRepositoryMock).getEmailsByCity(city);
        Assert.assertEquals(mockEmails, result);
    }
}