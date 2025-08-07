package com.safetynet.alerts.service;

import com.safetynet.alerts.domain.MedicalRecord;
import com.safetynet.alerts.repository.MedicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        log.info("Fetching all medical records");
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
        log.info("Medical record added: {} {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        return medicalRecord;
    }

    public boolean updateMedicalRecord(String firstName, String lastName, MedicalRecord updatedRecord) {
        log.info("Updating medical record for: {} {}", firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord != null) {
            medicalRecord.setBirthdate(updatedRecord.getBirthdate());
            medicalRecord.setMedications(updatedRecord.getMedications());
            medicalRecord.setAllergies(updatedRecord.getAllergies());
            log.info("Medical record updated for: {} {}", firstName, lastName);
            return true;
        }
        log.warn("Medical record not found for update: {} {}", firstName, lastName);
        return false;
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        log.info("Deleting medical record for: {} {}", firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordRepository.findByFirstNameAndLastName(firstName, lastName);

        if (medicalRecord != null) {
            medicalRecordRepository.delete(medicalRecord);
            log.info("Medical record deleted for: {} {}", firstName, lastName);
            return true;
        }
        log.warn("Medical record not found for deletion: {} {}", firstName, lastName);
        return false;
    }
}
