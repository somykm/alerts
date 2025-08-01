package com.safetynet.alerts.ControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PeopleController;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private Person samplePerson;

    @BeforeEach
    void setUp() {
        samplePerson = new Person("John", "Smith", "123 Street", "City", "12345", "1234567890", "john@example.com");
    }

    @Test
    void testGetPersonList() throws Exception {
        Mockito.when(personService.getAllPeople()).thenReturn(List.of(samplePerson));

        mockMvc.perform(get("/person/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testAddPerson() throws Exception {
        Mockito.when(personService.addPerson(any(Person.class))).thenReturn(samplePerson);

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePerson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Mockito.when(personService.updatePerson(eq("John"), eq("Doe"), any(Person.class))).thenReturn(true);

        mockMvc.perform(put("/person/John/Doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePerson)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testDeletePersonSuccess() throws Exception {
        Mockito.when(personService.deletePerson("John", "Doe")).thenReturn(true);

        mockMvc.perform(delete("/person/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person deleted successfully."));
    }

    @Test
    void testDeletePersonNotFound() throws Exception {
        Mockito.when(personService.deletePerson("John", "Doe")).thenReturn(false);

        mockMvc.perform(delete("/person/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person not found!"));
    }
}