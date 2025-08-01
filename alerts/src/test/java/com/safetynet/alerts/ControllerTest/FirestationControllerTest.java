package com.safetynet.alerts.ControllerTest;

import com.safetynet.alerts.controller.FirestationController;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.service.FirestationService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FirestationController.class)
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FirestationService firestationService;

    @Test
    void testGetFirestationsList() throws Exception {
        List<Firestation> mockList = List.of(new Firestation("123 Main St", "1"));
        when(firestationService.getAllFireStations()).thenReturn(mockList);

        mockMvc.perform(get("/firestations"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("123 Main St")));
    }

    @Test
    void testAddFirestation() throws Exception {
        Firestation mockFirestation = new Firestation("456 Oak Ave", "2");
        when(firestationService.addFirestation(any(Firestation.class))).thenReturn(mockFirestation);

        String jsonBody = """
                {
                    "address": "456 Oak Ave",
                    "station": "2"
                }
                """;

        mockMvc.perform(post("/firestations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("456 Oak Ave")));
    }

    @Test
    void testUpdateFirestation() throws Exception {
        when(firestationService.updateFirestation(eq("123 Main St"), any(Firestation.class))).thenReturn(true);

        String jsonBody = """
                {
                    "address": "123 Main St",
                    "station": "3"
                }
                """;

        mockMvc.perform(put("/firestations/123 Main St")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testDeleteFirestation_Success() throws Exception {
        when(firestationService.deleteFirestation("123 Main St")).thenReturn(true);

        mockMvc.perform(delete("/firestations/123 Main St"))
                .andExpect(status().isOk())
                .andExpect(content().string("Firestation deleted successfully!"));
    }

    @Test
    void testDeleteFirestation_NotFound() throws Exception {
        when(firestationService.deleteFirestation("999 Unknown Rd")).thenReturn(false);

        mockMvc.perform(delete("/firestations/999 Unknown Rd"))
                .andExpect(status().isOk())
                .andExpect(content().string("Firestation not found!"));
    }
}