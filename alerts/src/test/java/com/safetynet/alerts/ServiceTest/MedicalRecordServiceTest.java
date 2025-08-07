package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.service.MedicalRecordService;
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
public class MedicalRecordServiceTest {
    @Mock
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {
        medicalRecordRepository = mock(MedicalRecordRepository.class);
        medicalRecordService = new MedicalRecordService(medicalRecordRepository);
    }

    @Test
    void testGetAllMedicalRecordsFromRepositoryByFirstName_LastName() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("John");
        record.setLastName("Smith");

        when(medicalRecordRepository.findAll()).thenReturn(List.of(record));

        List<MedicalRecord> result = medicalRecordService.getAllMedicalRecords();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void testAddMedicalRecord() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Jane");
        record.setLastName("Smith");

        MedicalRecord result = medicalRecordService.addMedicalRecord(record);

        verify(medicalRecordRepository).save(record);
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void testUpdateMedicalRecordWhenSuccessful() {
        MedicalRecord existing = new MedicalRecord();
        existing.setFirstName("Sarah");
        existing.setLastName("Brown");

        MedicalRecord updated = new MedicalRecord();
        updated.setBirthdate("01/01/1995");
        updated.setMedications(List.of("med1"));
        updated.setAllergies(List.of("peanut"));

        when(medicalRecordRepository.findByFirstNameAndLastName("Sarah", "Brown")).thenReturn(existing);

        boolean result = medicalRecordService.updateMedicalRecord("Sarah", "Brown", updated);

        assertTrue(result);
        assertEquals("01/01/1995", existing.getBirthdate());
        assertEquals(List.of("med1"), existing.getMedications());
        assertEquals(List.of("peanut"), existing.getAllergies());
    }

    @Test
    void testUpdateMedicalRecordWhenNotFound() {
        when(medicalRecordRepository.findByFirstNameAndLastName("Bob", "Jackson")).thenReturn(null);

        MedicalRecord updated = new MedicalRecord();
        boolean result = medicalRecordService.updateMedicalRecord("Bob", "Jackson", updated);

        assertFalse(result);
    }

    @Test
    void testDeleteMedicalRecordWhenSuccessful() {
        MedicalRecord record = new MedicalRecord();
        record.setFirstName("Chase");
        record.setLastName("Smith");

        when(medicalRecordRepository.findByFirstNameAndLastName("Chase", "Smith")).thenReturn(record);

        boolean result = medicalRecordService.deleteMedicalRecord("Chase", "Smith");

        assertTrue(result);
        verify(medicalRecordRepository).delete(record);
    }

    @Test
    void testDeleteMedicalRecordWhenNotFound() {
        when(medicalRecordRepository.findByFirstNameAndLastName("Unknown", "Person")).thenReturn(null);

        boolean result = medicalRecordService.deleteMedicalRecord("Unknown", "Person");

        assertFalse(result);
        verify(medicalRecordRepository, never()).delete(any());
    }
}
