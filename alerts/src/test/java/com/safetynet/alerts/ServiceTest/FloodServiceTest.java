package com.safetynet.alerts.ServiceTest;

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
    void testGetAllHouseholdByFirestationReturnsCorrectData() {
        String stationNumber = "1";
        String address = "456 Oak Leaf St";
        List<String> stationNumbers = List.of(stationNumber);

        Firestation firestation = new Firestation(address, String.valueOf(stationNumber));
        Person person = new Person("Jane", "Doe", address, "City", "Zip", "123-456-5555", "jane@example.com");
        MedicalRecord medicalRecord = new MedicalRecord("Jane", "Doe", "01/01/1980",
                List.of("medication1"), List.of("allergy1"));

        when(jsonParser.getAllFirestation()).thenReturn(List.of(firestation));
        when(personRepository.findByAddressIn(List.of(address))).thenReturn(List.of(person));
        when(medicalRecordRepository.findByFirstNameAndLastName("Jane", "Doe")).thenReturn(medicalRecord);

        Map<String, List<Flood>> result = floodService.getAllHouseholdByFirestation(stationNumbers);

        assertTrue(result.containsKey(address));
        List<Flood> floods = result.get(address);
        assertEquals(1, floods.size());

        Flood flood = floods.getFirst();
        assertEquals("Jane", flood.getFirstName());
        assertEquals("Doe", flood.getLastName());
        assertEquals("123-456-5555", flood.getPhone());
        assertEquals(address, flood.getAddress());
        assertEquals(List.of(String.valueOf(stationNumber)), flood.getHouseHoldServedByStation());
        assertEquals(List.of("medication1"), flood.getMedications());
        assertEquals(List.of("allergy1"), flood.getAllergies());
        assertTrue(flood.getAge() > 30); // assuming current year > 2015
    }
}
