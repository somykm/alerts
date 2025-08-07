package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirestasionServiceTest {
    private FirestationRepository firestationRepository;
    private PersonRepository personRepository;
    private FirestationService firestationService;

    @BeforeEach
    void setUp() {
        firestationRepository = mock(FirestationRepository.class);
        personRepository = mock(PersonRepository.class);
        firestationService = new FirestationService(firestationRepository, personRepository);
    }

    @Test
    void testGetAllFireStationsFromFirestationRepository() {
        List<Firestation> mockList = List.of(new Firestation("123 Main St", "1"));
        when(firestationRepository.findAll()).thenReturn(mockList);

        List<Firestation> result = firestationService.getAllFireStations();
        assertEquals(1, result.size());
        assertEquals("123 Main St", result.get(0).getAddress());
    }

    @Test
    void testAddFirestation() {
        Firestation firestation = new Firestation("456 Oak Leaf Ave", "2");

        firestationService.addFirestation(firestation);

        verify(firestationRepository).save(firestation);
    }

    @Test
    void testUpdateFirestationWhenItSuccessed() {
        Firestation existing = new Firestation("567 Oak Leaf St", "1");
        Firestation updated = new Firestation("567 Oak Leaf St", "3");

        when(firestationRepository.findByAddress("567 Oak Leaf St")).thenReturn(existing);

        boolean result = firestationService.updateFirestation("567 Oak Leaf St", updated);

        assertTrue(result);
        assertEquals("567 Oak Leaf St", existing.getAddress());
        assertEquals("3", existing.getStation());
    }

    @Test
    void testUpdateFirestationWhenNotFound() {
        when(firestationRepository.findByAddress("Unknown")).thenReturn(null);

        boolean result = firestationService.updateFirestation("Unknown", new Firestation("Unknown", "5"));

        assertFalse(result);
    }

    @Test
    void testDeleteFirestationWhenSuccess() {
        Firestation firestation = new Firestation("567 Oak Leaf St", "1");
        when(firestationRepository.findByAddress("567 Oak Leaf St")).thenReturn(firestation);

        boolean result = firestationService.deleteFirestation("567 Oak Leaf St");

        assertTrue(result);
        verify(firestationRepository).delete(firestation);
    }

    @Test
    void testDeleteFirestationWhenNotFound() {
        when(firestationRepository.findByAddress("Unknown")).thenReturn(null);

        boolean result = firestationService.deleteFirestation("Unknown");

        assertFalse(result);
        verify(firestationRepository, never()).delete(any());
    }

    @Test
    void testGetAllPhoneNumbersByStationNumberFromStationList() {
        Firestation station1 = new Firestation("567 Oak Leaf St", "1");
        Firestation station2 = new Firestation("456 Oak Ave", "1");
        Person person1 = new Person();
        person1.setAddress("567 Oak Leaf St");
        person1.setPhone("202-555-5555");
        Person person2 = new Person();
        person2.setAddress("456 Oak Ave");
        person2.setPhone("222-222-2222");

        when(firestationRepository.findByStation("1")).thenReturn(List.of(station1, station2));
        when(personRepository.findByAddressIn(List.of("567 Oak Leaf St", "456 Oak Ave"))).thenReturn(List.of(person1, person2));

        List<String> phones = firestationService.getPhoneNumbersByFirestation("1");

        assertEquals(2, phones.size());
        assertTrue(phones.contains("202-555-5555"));
        assertTrue(phones.contains("222-222-2222"));
    }
}
