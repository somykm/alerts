package com.safetynet.alerts.RepositoryTest;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.JsonParser;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicalRecordRepositoryTest {
    @Mock
    private JsonParser jsonParser;

    @InjectMocks
    private MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    void setUp() {
        jsonParser = mock(JsonParser.class);
        medicalRecordRepository = new MedicalRecordRepository(jsonParser);
    }

    @Test
    void testFindAll() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Ali");
        record.setLastName("Smith");

        when(jsonParser.getAllMedicalRecords()).thenReturn(List.of(record));

        List<MedicalRecord> result = medicalRecordRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("Ali", result.getFirst().getFirstName());
    }

    @Test
    void testFindByFirstNameAndLastName_Found() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Bob");
        record.setLastName("Jackson");

        when(jsonParser.getAllMedicalRecords()).thenReturn(List.of(record));

        MedicalRecord result = medicalRecordRepository.findByFirstNameAndLastName("Bob", "Jackson");

        assertNotNull(result);
        assertEquals("Bob", result.getFirstName());
    }

    @Test
    void testFindByFirstNameAndLastName_NotFound() {
        when(jsonParser.getAllMedicalRecords()).thenReturn(List.of());

        MedicalRecord result = medicalRecordRepository.findByFirstNameAndLastName("Unknown", "Person");

        assertNull(result);
    }

    @Test
    void testSave() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Chase");
        record.setLastName("Smith");

        List<MedicalRecord> mockList = mock(List.class);
        when(jsonParser.getAllMedicalRecords()).thenReturn(mockList);

        medicalRecordRepository.save(record);

        verify(mockList).add(record);
    }

    @Test
    void testDelete() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Dana");
        record.setLastName("Robin");

        List<MedicalRecord> mockList = mock(List.class);
        when(jsonParser.getAllMedicalRecords()).thenReturn(mockList);

        medicalRecordRepository.delete(record);

        verify(mockList).remove(record);
    }

    @Test
    void testFindByLastNameWhenFound() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Eve");
        record.setLastName("Stone");

        when(jsonParser.getAllMedicalRecords()).thenReturn(List.of(record));

        MedicalRecord result = medicalRecordRepository.findByLastName("Stone");

        assertNotNull(result);
        assertEquals("Eve", result.getFirstName());
    }

    @Test
    void testFindByLastNameWhenNotFound() {
        when(jsonParser.getAllMedicalRecords()).thenReturn(List.of());

        MedicalRecord result = medicalRecordRepository.findByLastName("Something");

        assertNull(result);
    }
}


