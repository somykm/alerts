//package com.safetynet.alerts.repository;
//
//import com.safetynet.alerts.domain.Person;
//import com.safetynet.alerts.service.PersonService;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class PersonServiceTest {
//    @Mock
//    private PersonRepository personRepositoryMock;
//
//    @InjectMocks
//    private PersonService personService;// Inject mocks
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this); // Initialize mocks
//    }
//
//    @Test
//    public void testGetAllPeople(){
//        //arrange
//         //act
//        //assert
//    }
//    @Test
//    public void testFindPersonByFirstNameAndLastName(){
//        //arrage
//        Person mockPerson =new Person("Sara","Smith");
//        //when(personRepositoryMock.findByFirstNameAndLastName("Sara","Smith")).thenReturn(Optional.of(mockPerson));
//
//        //act
//        Person result= personService.findByFirstNameAndLastName("Sara","Smith");
//        //assert
////        assertNatnull(result);
////        assertEquals(result.getLastName("sara"));
////        assertNotNull(result.getLastName("Smith"));
//    }
//}
//
//
//
