package com.safetynet.alerts.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.safetynet.alerts.controller.PeopleController;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonService personServiceMock;
    @InjectMocks
    private PeopleController peopleController;

    @Test
    public void testGetPersonListFromPersonService() throws Exception {
        // Arrange
        Person mockPerson1 = new Person("John", "Doe", "4700 White oak",
                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");
        Person mockPerson2 = new Person("Sara", "Smith", "1235 White oak apt 12",
                "Silver Spring", "20812", "202-555-5757", "saraSmith@gmail.com");

        List<Person> mockList = Arrays.asList(mockPerson1, mockPerson2);
        Mockito.when(personServiceMock.getAllPeople()).thenReturn(mockList);

        // Act & Assert
        mockMvc.perform(get("/person"))  // Adjust the endpoint if needed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Sara"));
    }

    @Test
    public void testAddPerson() throws Exception {
        Person person = new Person("John", "Doe", "4777 White oak apt 5",
                "Silver Spring", "20812", "202-555-8888", "Johnny125@gmail.com");
        Mockito.when(personServiceMock.addPerson(Mockito.any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    public void testUpdatePerson() throws Exception {
        Person updatedPerson = new Person("John", "Doe", "4700 White oak",
                "Silver Spring", "20815", "202-555-8282", "Johnny@gmail.com");

        Mockito.when(personServiceMock.updatePerson("John", "Doe", updatedPerson)).thenReturn(true);

        mockMvc.perform(put("/person/John/Doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedPerson)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        Mockito.when(personServiceMock.deletePerson("John", "Doe")).thenReturn(true);

        mockMvc.perform(delete("/person/John/Doe"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("Person deleted successfully."));
    }
}