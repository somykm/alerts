package com.safetynet.alerts.RepositoryTest;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Flood;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;

import com.safetynet.alerts.service.FloodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    private PersonRepository personRepository;
    private JsonParser jsonParser;

    @InjectMocks
    private FloodService floodService;

    @BeforeEach
    void setUp() {
        medicalRecordRepository = mock(MedicalRecordRepository.class);
        personRepository = mock(PersonRepository.class);
        jsonParser = mock(JsonParser.class);
        floodService = new FloodService(medicalRecordRepository, personRepository, jsonParser);
    }

    @Test
    void testGetAllHouseholdByFirestation() {
        // Setup mock firestation
        Firestation firestation = new Firestation();
        firestation.setAddress("555 Bradley St");
        firestation.setStation("1");

        when(jsonParser.getAllFirestation()).thenReturn(List.of(firestation));

        // Setup mock person
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("555 Bradley St");
        person.setPhone("123-456-5555");

        when(personRepository.findByAddressIn(List.of("555 Bradley St"))).thenReturn(List.of(person));

        // Setup mock medical record
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Doe");
        record.setBirthdate("01/01/2005");
        record.setMedications(List.of("med1"));
        record.setAllergies(List.of("peanut"));

        when(medicalRecordRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(record);

        // Execute
        Map<String, List<Flood>> result = floodService.getAllHouseholdByFirestation(List.of("1"));

        // Verify
        assertTrue(result.containsKey("555 Bradley St"));
        List<Flood> floods = result.get("555 Bradley St");
        assertEquals(1, floods.size());

        Flood flood = floods.get(0);
        assertEquals("John", flood.getFirstName());
        assertEquals("Doe", flood.getLastName());
        assertEquals("123-456-5555", flood.getPhone());
        assertEquals("555 Bradley St", flood.getAddress());
        assertEquals(List.of("1"), flood.getHouseHoldServedByStation());
        assertEquals(List.of("med1"), flood.getMedications());
        assertEquals(List.of("peanut"), flood.getAllergies());
    }
}
