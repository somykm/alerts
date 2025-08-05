package com.safetynet.alerts.ServiceTest;

import com.safetynet.alerts.domain.ChildAlert;
import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.domain.Person;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import com.safetynet.alerts.repository.PersonRepository;
import com.safetynet.alerts.service.ChildAlertService;
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
public class ChildAlertServiceTest {
    @Mock
    private PersonRepository personRepository;
    private MedicalRecordRepository medicalRecordRepository;
    @InjectMocks
    private ChildAlertService childAlertService;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        medicalRecordRepository = mock(MedicalRecordRepository.class);
        childAlertService = new ChildAlertService(personRepository, medicalRecordRepository);
    }

    @Test
    void testGetChildInfoByAddressReturnsChildWithHouseholdMembers() {
        String address = "456 Oak Leaf St";

        Person child = new Person("Tommy", "Doe", address, "City", "Zip", "123-456-5555", "tommy@example.com");
        Person adult = new Person("Jonathan", "Doe", address, "City", "Zip", "987-654-3333", "johny@example.com");

        MedicalRecord childRecord = new MedicalRecord("Tommy", "Doe", "01/01/2015", List.of(), List.of());
        MedicalRecord adultRecord = new MedicalRecord("Jonathan", "Doe", "01/01/1980", List.of(), List.of());

        when(personRepository.findByAddressIn(List.of(address))).thenReturn(List.of(child, adult));
        when(medicalRecordRepository.findByFirstNameAndLastName("Tommy", "Doe")).thenReturn(childRecord);
        when(medicalRecordRepository.findByFirstNameAndLastName("Jonathan", "Doe")).thenReturn(adultRecord);

        List<ChildAlert> result = childAlertService.getChildInfoByAddress(address);

        assertEquals(1, result.size());
        ChildAlert alert = result.getFirst();
        assertEquals("Tommy", alert.getFirstName());
        assertEquals("Doe", alert.getLastName());
        assertTrue(alert.getAge() <= 18);
        assertEquals(List.of("Jonathan Doe"), alert.getHouseholdMembers());
    }

    @Test
    void testGetChildInfoByAddressReturnsEmptyWhenNoChildren() {
        String address = "456 Oak Ave";

        Person adult1 = new Person("Ali", "Smith", address, "City", "Zip", "111-222-3333", "ali@example.com");
        Person adult2 = new Person("Bob", "Smith", address, "City", "Zip", "444-555-6666", "bob@example.com");

        MedicalRecord record1 = new MedicalRecord("Ali", "Smith", "01/01/1980", List.of(), List.of());
        MedicalRecord record2 = new MedicalRecord("Bob", "Smith", "01/01/1975", List.of(), List.of());

        when(personRepository.findByAddressIn(List.of(address))).thenReturn(List.of(adult1, adult2));
        when(medicalRecordRepository.findByFirstNameAndLastName("Ali", "Smith")).thenReturn(record1);
        when(medicalRecordRepository.findByFirstNameAndLastName("Bob", "Smith")).thenReturn(record2);

        List<ChildAlert> result = childAlertService.getChildInfoByAddress(address);

        assertTrue(result.isEmpty());
    }
}
