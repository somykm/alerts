package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.FireResidents;
import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.FireService;
import com.safetynet.alerts.service.FirestationService;
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
public class FireServiceTest {
    @Mock
    private PersonRepository personRepository;
    private FirestationService firestationService;
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private FireService fireService;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        firestationService = mock(FirestationService.class);
        medicalRecordRepository = mock(MedicalRecordRepository.class);
        fireService = new FireService(personRepository, firestationService, medicalRecordRepository);
    }

    @Test
    void testGetResidentAndStationByAddressReturnsCorrectData() {
        String address = "456 Oak Leaf St";
        List<String> addresses = Collections.singletonList(address);

        Person person = new Person("John", "Doe", address, "City", "Zip", "123-456-5555", "john@example.com");
        Firestation firestation = new Firestation(address, "3");
        MedicalRecord medicalRecord = new MedicalRecord("John", "Doe", "01/01/1990",
                Arrays.asList("medication1", "medication2"), List.of("allergy1"));


        when(personRepository.findByAddressIn(addresses)).thenReturn(Collections.singletonList(person));
        when(firestationService.getFirestationByAddress(address)).thenReturn(firestation);
        when(medicalRecordRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(medicalRecord);

        List<FireResidents> result = fireService.getResidentAndStationByAddress(addresses);

        assertEquals(1, result.size());
        FireResidents resident = result.getFirst();

        assertEquals("John", resident.getFirstName());
        assertEquals("Doe", resident.getLastName());
        assertEquals(address, resident.getAddress());
        assertEquals("123-456-5555", resident.getPhone());
        assertEquals("3", resident.getStation());
        assertEquals(Arrays.asList("medication1", "medication2"), resident.getMedications());
        assertEquals(List.of("allergy1"), resident.getAllergies());
        assertTrue(resident.getAge() > 30); // assuming current year > 2020
    }
}

