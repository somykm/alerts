import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.controller.PeopleController;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.reflect.Array.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PeopleController.class)
@RunWith(MockitoJUnitRunner.class)
public class PeopleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersonService personService;
    @InjectMocks
    private PeopleController peopleController;

    @Test
    void testGetPersonList() {
        // Arrange
        List<Person> mockPeople = List.of(
                new Person("John", "Doe"),
                new Person("Jane", "Aston")
        );

        Mockito.when(personService.getAllPeople()).thenReturn(mockPeople);

        //Act & Assert
        mockMvc.perform(get("/person/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person updatedPerson = new Person("John", "Smith");
        Mockito.when(personService.updatePerson("John", "Doe", updatedPerson)).thenReturn(true);

        mockMvc.perform(put("/person/John/Doe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedPerson)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testDeletePerson() throws Exception {
        Mockito.when(personService.deletePerson("John", "Doe")).thenReturn(true);

        mockMvc.perform(delete("/person/John/Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("Person deleted successfully."));
    }
}

