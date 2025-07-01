//package com.safetynet.alerts;
//
//import com.safetynet.alerts.controller.PeopleController;
//import com.safetynet.alerts.domain.Person;
//import com.safetynet.alerts.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//@WebMvcTest(PeopleController.class)
//@RunWith(MockitoJUnitRunner.class)
//public class PersonControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private PersonService personServiceMock;
//    @InjectMocks
//    private PeopleController peopleController;
//
//    @Test
//    public void testGetPersonListFromPersonService() {
//        //Arrange
//        Person mockPeople1 =
//                new Person("John", "Doe", "4700 White oak",
//                        "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");
//        Person mockPerson2 = new Person("Sara", "Smith", "1235 White oak apt 12",
//                "Silver Spring", "20812", "202-555-5757", "saraSmith@gmail.com");
//
//        List<Person> mockList = Arrays.asList(mockPeople1, mockPerson2);
//        Mockito.when(personServiceMock.getAllPeople()).thenReturn(mockList);
//        //act
//        List<Person> result = personServiceMock.getAllPeople();
//        // Assert
//        Assert.assertTrue(result.size() == 2);
//        Assert.assertTrue(result.get(0).getFirstName().equals("John"));
//        Assert.assertTrue(result.get(1).getFirstName().equals("Sara"));
//    }
//
//    @Test
//    void testUpdatePerson() throws Exception {
//        Person updatedPerson = new Person("John", "Doe", "4700 White oak",
//                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");
//        Mockito.when(personServiceMock.updatePerson("John", "Doe", updatedPerson)).thenReturn(true);
//
//
//    }
//}
