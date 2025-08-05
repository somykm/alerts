package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.FireResidents;
import com.safetynet.alerts.domain.FirestationArea;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.service.FireService;
import com.safetynet.alerts.service.FirestationAreaService;
import com.safetynet.alerts.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirestationAreaServiceTest {
    @Mock
    private FireService fireService;
    private FirestationService firestationService;

    @InjectMocks
    private FirestationAreaService firestationAreaService;

    @BeforeEach
    void setUp() {
        fireService = mock(FireService.class);
        firestationService = mock(FirestationService.class);
        firestationAreaService = new FirestationAreaService(fireService, firestationService);
    }

    @Test
    void testGetResidentInfoByStationReturnsCorrectCountsAndData() {
        String station = "1";
        List<String> addresses = List.of("456 Oak Leaf st", "456 White Oak Ave");

        FireResidents child = new FireResidents(
                "1", "456 Oak Leaf st", "Tommy", "Doe", "123-456-5555", 10,
                List.of("Ibuprofen"), List.of("Peanuts")
        );

        FireResidents adult = new FireResidents(
                "1", "456 White Oak Ave", "Janny", "Doe", "987-654-7777", 35,
                List.of("Aspirin"), List.of()
        );

        when(firestationService.getAddressesByStation(station)).thenReturn(addresses);
        when(fireService.getResidentAndStationByAddress(addresses)).thenReturn(List.of(child, adult));

        FirestationArea result = firestationAreaService.getResidentInfoByStation(station);

        assertEquals(2, result.getResidents().size());
        assertEquals(1, result.getChildCount());
        assertEquals(1, result.getAdultCount());

        Person first = result.getResidents().getFirst();
        assertEquals("Tommy", first.getFirstName());
        assertEquals("Doe", first.getLastName());
        assertEquals("456 Oak Leaf st", first.getAddress());
        assertEquals("123-456-5555", first.getPhone());

        Person second = result.getResidents().get(1);
        assertEquals("Janny", second.getFirstName());
        assertEquals("Doe", second.getLastName());
        assertEquals("456 White Oak Ave", second.getAddress());
        assertEquals("987-654-7777", second.getPhone());
    }

    @Test
    void testGetResidentInfoByStationReturnsEmptyWhenNoResidents() {
        String station = "2";
        List<String> addresses = List.of();

        when(firestationService.getAddressesByStation(station)).thenReturn(addresses);
        when(fireService.getResidentAndStationByAddress(addresses)).thenReturn(List.of());

        FirestationArea result = firestationAreaService.getResidentInfoByStation(station);

        assertTrue(result.getResidents().isEmpty());
        assertEquals(0, result.getChildCount());
        assertEquals(0, result.getAdultCount());
    }
}
