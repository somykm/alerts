package com.safetynet.alerts;

import com.safetynet.alerts.domain.Firestation;
import com.safetynet.alerts.repository.FirestationRepository;
import com.safetynet.alerts.repository.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirestationRepositoryTest {
    @Mock
    private JsonParser jsonParser;

    @InjectMocks
    private FirestationRepository firestationRepository;

    private List<Firestation> mockFirestations;

    @BeforeEach
    void setUp() {
        mockFirestations = new ArrayList<>();

        mockFirestations.add(new Firestation("123 Main St", "1"));
        mockFirestations.add(new Firestation("456 Elm St", "2"));
        mockFirestations.add(new Firestation("789 Oak St", "1"));
        when(jsonParser.getAllFirestation()).thenAnswer(invocation -> mockFirestations);
    }

    @Test
    void testFindAll() {
        List<Firestation> result = firestationRepository.findAll();
        Assertions.assertEquals(3, result.size());
    }
//    @Test
//    void testIfStation_Can_FindByAddress() {
//        Firestation result = firestationRepository.findByAddress("456 Elm St");
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("2", result.getStation());
//    }
    @Test
    void testIfStation_CanNot_FindByAddress() {
        Firestation result = firestationRepository.findByAddress("000 Unknown St");
        Assertions.assertNull(result);
    }

//    @Test
//    void testDelete() {
//        Firestation toDelete = mockFirestations.getFirst();
//        firestationRepository.delete(toDelete);
//        // Check that the list no longer contains the deleted item
//        Assertions.assertTrue(mockFirestations.contains(toDelete));
//    }


    @Test
    void testIfCanFindStationByStationNumberIf_NotFound() {
        List<Firestation> result = firestationRepository.findByStation("999");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testFindByAddressWithNullEntry() {
        mockFirestations.add(new Firestation(null, "3"));
        Firestation result = firestationRepository.findByAddress("Some Address");
        Assertions.assertNull(result);
    }
}
